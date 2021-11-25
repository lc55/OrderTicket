package com.lchao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.entity.AllTicket;
import com.lchao.entity.Line;
import com.lchao.entity.Site;
import com.lchao.entity.Train;
import com.lchao.enums.PriceBase;
import com.lchao.mapper.AllTicketMapper;
import com.lchao.service.*;
import com.lchao.util.DateUtils;
import com.lchao.util.Distance;
import com.lchao.util.FormatStringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class IAllTicketServiceImpl extends ServiceImpl<AllTicketMapper, AllTicket> implements IAllTicketService {

    @Autowired
    private AllTicketMapper allTicketMapper;
    @Autowired
    private ILineService iLineService;
    @Autowired
    private ITrainService iTrainService;
    @Autowired
    private ISiteService iSiteService;
    @Autowired
    private ISoldTicketService iSoldTicketService;

    @Override
    public Result searchTicket(Integer startId, Integer endId, String startDate, String types) {

        if (startId == null || endId == null || StringUtils.isBlank(startDate)) {
            return Result.Error("参数存在空值！");
        }
        Map<String, Object> startMap = iSiteService.getMap(new QueryWrapper<Site>().select("longitude", "latitude").eq("id", startId));
        Map<String, Object> endMap = iSiteService.getMap(new QueryWrapper<Site>().select("longitude", "latitude").eq("id", endId));
        if (MapUtils.isEmpty(startMap) || MapUtils.isEmpty(endMap)) {
            return Result.Error("起点或终点不存在！");
        }
        // 判断时间如果在今天之前直接返回
        if (DateUtils.isBefore(startDate)) {
            return Result.Error("出发时间不能在今天之前！");
        }

        // 通过站点寻找车次
        List<Map<String, Object>> startList = iLineService.listMaps(new QueryWrapper<Line>().select("train_id", "site_order").eq("site_id", startId));
        List<Map<String, Object>> endList = iLineService.listMaps(new QueryWrapper<Line>().select("train_id", "site_order").eq("site_id", endId));
        // 判断站点顺序
        List<Integer> trainIds = new CopyOnWriteArrayList<>();
        for (Map<String, Object> start : startList) {
            Integer startTrainId = MapUtils.getInteger(start, "trainId");
            Integer startSiteOrder = MapUtils.getInteger(start, "siteOrder");
            for (Map<String, Object> end : endList) {
                Integer endTrainId = MapUtils.getInteger(end, "trainId");
                Integer endSiteOrder = MapUtils.getInteger(end, "siteOrder");
                if (Objects.equals(startTrainId, endTrainId) && startSiteOrder < endSiteOrder) {
                    trainIds.add(startTrainId);
                }
            }
        }
        // 条件查询
        if (StringUtils.isNotBlank(types)) {
            List<Integer> typeList = FormatStringUtil.idsToList(types, ",");
            for (Integer trainId : trainIds) {
                int count = iTrainService.count(new QueryWrapper<Train>().eq("id", trainId).in("type", typeList));
                if (count == 0) {
                    trainIds.remove(trainId);
                }
            }
        }
        // 判断是否是当天
        boolean isToday = DateUtils.isToday(startDate);
        if (isToday) {
            // 如果是当天需要排除已经过时的车次
            String minTime = DateUtils.getNowAdd8();
            for (Integer trainId : trainIds) {
                Train train = iTrainService.getOne(new QueryWrapper<Train>().eq("id", trainId));
                String sTime = train.getStartTime();
                Line l = iLineService.getOne(new QueryWrapper<Line>().eq("train_id", trainId).eq("site_id", startId));
                String useTime = l.getTimeConsume();
                String newStartTime = DateUtils.addMinute(sTime, Integer.parseInt(useTime));
                if (DateUtils.checkTime(newStartTime, minTime)) {
                    trainIds.remove(trainId);
                }
            }
        }
        // 如果trainIds为空了直接返回
        if (trainIds.size() == 0) {
            return new Result(new ArrayList<Map<String, Object>>());
        }
        // 查询符合条件车次的基本信息
        List<Map<String, Object>> trainInfoList = iTrainService.getBaseTrainInfoList(trainIds);
        for (Map<String, Object> map : trainInfoList) {
            Integer trainId = MapUtils.getInteger(map, "id");
            // 计算出发和到站时间
            String sTime = MapUtils.getString(map, "startTime");
            Integer sConsume = iLineService.getObj(new QueryWrapper<Line>().select("time_consume").eq("train_id", trainId).eq("site_id", startId), obj -> Integer.parseInt(obj.toString()));
            Integer eConsume = iLineService.getObj(new QueryWrapper<Line>().select("time_consume").eq("train_id", trainId).eq("site_id", endId), obj -> Integer.parseInt(obj.toString()));
            String startDateTime = DateUtils.getEndDateTime(startDate, sTime, sConsume);
            String endDateTime = DateUtils.getEndDateTime(startDate, sTime, eConsume);
            String[] start = startDateTime.split(" ");
            String[] end = endDateTime.split(" ");
            map.put("startTime", start[1]);
            map.put("endTime", end[1]);
            map.put("endDate", end[0]);
            // 存起点和终点
            String startName = iSiteService.getObj(new QueryWrapper<Site>().select("site_name").eq("id", startId), Object::toString);
            String endName = iSiteService.getObj(new QueryWrapper<Site>().select("site_name").eq("id", endId), Object::toString);
            if (StringUtils.isBlank(startName) || StringUtils.isBlank(endName)) {
                return Result.Error("起点或终点不存在！");
            }
            map.put("startName", startName);
            map.put("endName", endName);
            // 当前车票值
            Integer curValue = iLineService.getCurValue(trainId, startId, endId);
            // 查询所有的车票数
            List<Map<String, Object>> allTicketList = allTicketMapper.getAllTicket(trainId);
            // 查询已经买过的车票
            List<Map<String, Object>> soldTicketList = iSoldTicketService.getSoldTicket(trainId, curValue, startDate);
            // 统计能够购买的票
            for (Map<String, Object> ticket : allTicketList) {
                Integer levelCar = MapUtils.getInteger(ticket, "levelCar");
                Integer seatCount = MapUtils.getInteger(ticket, "seatCount");
                for (Map<String, Object> soldTicket : soldTicketList) {
                    Integer soldLevelCar = MapUtils.getInteger(soldTicket, "levelCar");
                    Integer soldSeatCount = MapUtils.getInteger(soldTicket, "seatCount");
                    if (Objects.equals(soldLevelCar, levelCar)) {
                        Integer canBuyCount = seatCount - soldSeatCount;
                        ticket.put("seatCount", canBuyCount);
                    }
                }
            }
            for (Map<String, Object> enableMap : allTicketList) {
                Integer levelCar = MapUtils.getInteger(enableMap, "levelCar");
                map.put(levelCar == 1 ? "luxury" : (levelCar == 2 ? "ordinal" : "econ"), MapUtils.getInteger(enableMap, "seatCount"));
                // 计算车票
                String startLongitude = MapUtils.getString(startMap, "longitude");
                String startLatitude = MapUtils.getString(startMap, "latitude");
                String endLongitude = MapUtils.getString(endMap, "longitude");
                String endLatitude = MapUtils.getString(endMap, "latitude");
                // 计算起点终点坐标
                String[] startCoords = Distance.getCoordinate(startLongitude, startLatitude);
                String[] endCoords = Distance.getCoordinate(endLongitude, endLatitude);
                // 计算距离
                Double distance = Distance.getDistance(startCoords, endCoords);
                // 结果保留两位小数
                double econ = PriceBase.econ.getPriceBase() * distance;
                double ordinal = PriceBase.ordinal.getPriceBase() * distance;
                double luxury = PriceBase.luxury.getPriceBase() * distance;
                BigDecimal econPrice = new BigDecimal(econ).setScale(2, RoundingMode.HALF_UP);
                BigDecimal ordinalPrice = new BigDecimal(ordinal).setScale(2, RoundingMode.HALF_UP);
                BigDecimal luxuryPrice = new BigDecimal(luxury).setScale(2, RoundingMode.HALF_UP);

                map.put(levelCar == 1 ? "luxuryPrice" : (levelCar == 2 ? "ordinalPrice" : "econPrice"), levelCar == PriceBase.luxury.getType() ? luxuryPrice :
                        (levelCar == PriceBase.ordinal.getType() ? ordinalPrice : econPrice));
            }
            map.put("startDate", startDate);
            map.put("startId", startId);
            map.put("endId", endId);
        }
        return new Result(trainInfoList);
    }

    @Override
    public Map<String, Object> getMoreTicket(Integer id, Integer curValue, Integer levelCar, String startDate) {
        return allTicketMapper.getMoreTicket(id, curValue, levelCar, startDate);
    }
}