package com.lchao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.entity.*;
import com.lchao.enums.AddOrUpdate;
import com.lchao.enums.SeatUse;
import com.lchao.enums.TrainState;
import com.lchao.mapper.TrainMapper;
import com.lchao.service.*;
import com.lchao.util.DateUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ITrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements ITrainService {

    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private ILineService iLineService;
    @Autowired
    private ITrainCarriageService iTrainCarriageService;
    @Autowired
    private ISiteService iSiteService;
    @Autowired
    private ICarriageService iCarriageService;
    @Autowired
    private IAllTicketService iAllTicketService;
    @Autowired
    private ISeatService iSeatService;

    @Transactional
    @Override
    public Result addOrUpdateTrain(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        Integer addOrUpdate = jsonObject.getInteger("addOrUpdate");
        String trainNumber = jsonObject.getString("trainNumber");
        Integer isReturn = jsonObject.getInteger("isReturn");
        Integer type = jsonObject.getInteger("type");
        String startTime = jsonObject.getString("startTime");
        Integer trainCount = jsonObject.getInteger("trainCount");
        JSONArray siteList = jsonObject.getJSONArray("siteList");
        JSONArray carriageList = jsonObject.getJSONArray("carriageList");
        if (StringUtils.isBlank(trainNumber)
                || isReturn == null
                || type == null
                || StringUtils.isBlank(startTime)
                || trainCount == null
        ) {
            return Result.Error("参数存在空值！");
        }
        if (siteList == null || siteList.isEmpty() || carriageList == null || carriageList.isEmpty()) {
            return Result.Error("站点和车厢信息为空！");
        }
        Train t = getOne(new QueryWrapper<Train>().eq("train_number", trainNumber));
        // 填充train
        Train train = new Train();

        if (addOrUpdate == AddOrUpdate.ADD.getValue()) {
            if (t != null) {
                return Result.Error("车次编号为：" + trainNumber + "的车次已经存在了！");
            }
        } else if (addOrUpdate == AddOrUpdate.UPDATE.getValue()) {
            if (t == null) {
                return Result.Error("车次编号为：" + trainNumber + "的车次不存在！");
            } else {
                train.setId(t.getId());
                // 删除以前的line
                boolean removeLine = iLineService.remove(new QueryWrapper<Line>().eq("train_id", t.getId()));
                if (!removeLine) {
                    return Result.RollBackError("删除线路失败！");
                }
                // 删除trainCarriage
                boolean removeTrainCarriage = iTrainCarriageService.remove(new QueryWrapper<TrainCarriage>().eq("train_id", t.getId()));
                if (!removeTrainCarriage) {
                    return Result.RollBackError("删除车厢车次表失败！");
                }
                // 删除以前的票
                boolean removeAllTicket = iAllTicketService.remove(new QueryWrapper<AllTicket>().eq("train_id", t.getId()));
                if (!removeAllTicket) {
                    return Result.RollBackError("删除车票失败！");
                }
            }
        } else {
            return Result.RollBackError("错误的标志！");
        }

        train.setTrainNumber(trainNumber);
        train.setIsReturn(isReturn);
        train.setType(type);
        train.setStartTime(startTime);
        train.setShift(trainCount);
        train.setCarriage(carriageList.size());
        train.setTrainStatus(TrainState.disable.getState());
        JSONObject siteStart = siteList.getJSONObject(0);
        JSONObject siteEnd = siteList.getJSONObject(siteList.size() - 1);
        train.setStartId(siteStart.getInteger("siteId"));
        train.setEndId(siteEnd.getInteger("siteId"));
        Integer timeConsume = 0;
        for (Object o : siteList) {
            String s = JSONObject.toJSONString(o);
            JSONObject json = JSONObject.parseObject(s);
            Integer time = json.getInteger("useTime");
            timeConsume += time;
        }
        train.setTimeConsume(DateUtils.minuteToTime(timeConsume));
        train.setEndTime(DateUtils.addMinute(train.getStartTime(), timeConsume));
        if (addOrUpdate == AddOrUpdate.ADD.getValue()) {
            boolean save = save(train);
            if (!save) {
                return Result.RollBackError("添加车次失败！");
            }
        } else if (addOrUpdate == AddOrUpdate.UPDATE.getValue()) {
            boolean b = updateById(train);
            if (!b) {
                return Result.RollBackError("更新车次失败！");
            }
        } else {
            return Result.RollBackError("错误的标志！");
        }

        // 填充line
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < siteList.size(); i++) {
            Line line = new Line();
            line.setTrainId(train.getId());
            line.setSiteId(siteList.getJSONObject(i).getInteger("siteId"));
            Integer useTime = siteList.getJSONObject(i).getInteger("useTime");
            line.setTimeConsume(useTime.toString());
            line.setSiteOrder(i);
            line.setSiteValue(new Double(Math.pow(2, i)).intValue());
            lineList.add(line);
        }
        boolean saveLine = iLineService.saveBatch(lineList);
        if (!saveLine) {
            return Result.RollBackError("添加线路失败！");
        }
        // 填充车厢车次关系表
        List<TrainCarriage> trainCarriageList = new ArrayList<>();
        System.out.println(JSONObject.toJSONString(carriageList));
        for (int i = 0; i < carriageList.size(); i++) {
            TrainCarriage trainCarriage = new TrainCarriage();
            trainCarriage.setTrainId(train.getId());
            trainCarriage.setCarriageId(carriageList.getInteger(i));
            trainCarriage.setCarriageOrder(i + 1);
            trainCarriageList.add(trainCarriage);
        }
        boolean saveTrainCarriage = iTrainCarriageService.saveBatch(trainCarriageList);
        if (!saveTrainCarriage) {
            return Result.RollBackError("添加车厢车次关系失败！");
        }
        // 给该车次生成车票
        List<AllTicket> allTickets = new ArrayList<>();
        for (int i = 0; i < carriageList.size(); i++) {
            Integer carriageId = carriageList.getInteger(i);
            List<Seat> seatList = iSeatService.list(new QueryWrapper<Seat>().eq("carriage_id", carriageId));
            for (Seat seat : seatList) {
                AllTicket allTicket = new AllTicket();
                allTicket.setTrainId(train.getId());
                allTicket.setCarriageId(carriageId);
                allTicket.setSeatId(seat.getId());
                allTicket.setState(SeatUse.NO.getValue());
                allTickets.add(allTicket);
            }
        }
        boolean saveAllTicket = iAllTicketService.saveBatch(allTickets);
        if (!saveAllTicket) {
            return Result.RollBackError("生成车票失败！");
        }
        return Result.OK();
    }

    @Override
    public Result getTrainList(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return Result.Error("分页参数存在空值");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        List<Map<String, Object>> mapList = trainMapper.getTrainList(page);
        return new Result(mapList);
    }

    @Override
    public Result getTrainInfo(Integer id) {
        if (id == null) {
            return Result.Error("参数存在空值");
        }
        // 获取基础数据
        Map<String, Object> baseInfo = getMap(new QueryWrapper<Train>().eq("id", id));
        // 获取站点信息
        List<Map<String, Object>> siteList = iLineService.listMaps(new QueryWrapper<Line>().select("site_id", "time_consume").eq("train_id", id).orderByAsc("site_value"));
        for (Map<String, Object> map : siteList) {
            Integer siteId = MapUtils.getInteger(map, "siteId");
            Map<String, Object> m = iSiteService.getMap(new QueryWrapper<Site>().select("site_name").eq("id", siteId));
            map.putAll(m);
        }
        // 获取车厢信息
        List<Map<String, Object>> carriageList = iTrainCarriageService.listMaps(new QueryWrapper<TrainCarriage>().select("carriage_id", "carriage_order").eq("train_id", id).orderByAsc("carriage_order"));
        for (Map<String, Object> map : carriageList) {
            Integer carriageId = MapUtils.getInteger(map, "carriageId");
            Map<String, Object> m = iCarriageService.getMap(new QueryWrapper<Carriage>().select("carriage_number", "price_base", "seat_count", "level_car").eq("id", carriageId));
            map.putAll(m);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("baseInfo", baseInfo);
        map.put("siteList", siteList);
        map.put("carriageList", carriageList);
        return new Result(map);
    }

    @Override
    public List<Map<String, Object>> getBaseTrainInfoList(List<Integer> trainIds) {
        return trainMapper.getBaseTrainInfoList(trainIds);
    }
}
