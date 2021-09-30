package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.entity.Carriage;
import com.lchao.entity.Seat;
import com.lchao.entity.TrainCarriage;
import com.lchao.enums.PriceBase;
import com.lchao.enums.SeatUse;
import com.lchao.mapper.CarriageMapper;
import com.lchao.service.ICarriageService;
import com.lchao.service.ISeatService;
import com.lchao.service.ITrainCarriageService;
import com.lchao.util.SeatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ICarriageServiceImpl extends ServiceImpl<CarriageMapper, Carriage> implements ICarriageService {

    @Autowired
    private ISeatService iSeatService;
    @Autowired
    private ITrainCarriageService iTrainCarriageService;

    @Transactional
    @Override
    public Result addCarriage(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        String carriageNumber = jsonObject.getString("carriageNumber");
        Integer sizeLeft = jsonObject.getInteger("sizeLeft");
        Integer sizeRight = jsonObject.getInteger("sizeRight");
        Integer sizeRow = jsonObject.getInteger("sizeRow");
        Integer seatCount = jsonObject.getInteger("seatCount");
        Integer level = jsonObject.getInteger("level");
        Integer adapter = jsonObject.getInteger("adapter");
        if (StringUtils.isBlank(carriageNumber) ||
                sizeLeft == null ||
                sizeRight == null ||
                sizeRow == null ||
                seatCount == null ||
                level == null ||
                adapter == null) {
            return Result.Error("参数存在空值！");
        }
        int count = count(new QueryWrapper<Carriage>().eq("carriage_number", carriageNumber));
        if (count > 0) {
            return Result.Error("车厢编号为：" + carriageNumber + "的车厢已经存在了！");
        }
        // 生成车厢
        Carriage carriage = new Carriage();
        carriage.setCarriageNumber(carriageNumber);
        carriage.setPriceBase(level == PriceBase.econ.getType() ? PriceBase.econ.getPriceBase() :
                (level == PriceBase.ordinal.getType() ? PriceBase.ordinal.getPriceBase() : PriceBase.luxury.getPriceBase()));
        carriage.setLeftSeat(sizeLeft);
        carriage.setRightSeat(sizeRight);
        carriage.setRowSeat(sizeRow);
        carriage.setSeatCount(seatCount);
        carriage.setLevelCar(level);
        carriage.setAdaptModel(adapter);
        boolean save = save(carriage);
        if (!save) {
            return Result.RollBackError("添加车厢失败！");
        }
        // 给车厢生成座位表
        Result error = generateSeats(sizeLeft, sizeRight, sizeRow, carriage);
        if (error != null) {
            return error;
        }
        return Result.OK();
    }

    private Result generateSeats(Integer sizeLeft, Integer sizeRight, Integer sizeRow, Carriage carriage) {
        List<String> seatNumberList = SeatUtil.generateSeats(sizeLeft, sizeRight, sizeRow);
        List<Seat> seatList = new ArrayList<>();
        for (String s : seatNumberList) {
            Seat seat = new Seat();
            seat.setSeatNumber(s);
            seat.setCarriageId(carriage.getId());
            seatList.add(seat);
        }
        boolean saveBatch = iSeatService.saveBatch(seatList);
        if (!saveBatch) {
            return Result.RollBackError("生成座位失败！");
        }
        return null;
    }

    @Override
    public Result getCarriageList(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            return Result.Error("分页参数为空");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        IPage<Map<String, Object>> mapIPage = pageMaps(page);
        return Result.page(mapIPage);
    }

    @Override
    public Result getCarriageInfo(Integer id) {

        if (id == null) {
            return Result.Error("车厢 id 为空");
        }
        Carriage carriage = getById(id);
        if (carriage == null) {
            return Result.Error("车厢不存在！");
        }
        return new Result(carriage);
    }

    @Transactional
    @Override
    public Result editCarriage(JSONObject jsonObject) {

        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        Integer id = jsonObject.getInteger("id");
        Long priceBase = jsonObject.getLong("priceBase");
        Integer sizeLeft = jsonObject.getInteger("sizeLeft");
        Integer sizeRight = jsonObject.getInteger("sizeRight");
        Integer sizeRow = jsonObject.getInteger("sizeRow");
        Integer seatCount = jsonObject.getInteger("seatCount");
        Integer level = jsonObject.getInteger("level");
        Integer adapter = jsonObject.getInteger("adapter");
        if (id == null ||
                priceBase == null ||
                sizeLeft == null ||
                sizeRight == null ||
                sizeRow == null ||
                seatCount == null ||
                level == null ||
                adapter == null) {
            return Result.Error("参数存在空值！");
        }

        Carriage car = getById(id);
        if (car == null) {
            return Result.Error("车厢不存在！");
        }
        Carriage carriage = new Carriage();
        carriage.setId(id);
        carriage.setPriceBase(priceBase);
        carriage.setLeftSeat(sizeLeft);
        carriage.setRightSeat(sizeRight);
        carriage.setRowSeat(sizeRow);
        carriage.setSeatCount(seatCount);
        carriage.setLevelCar(level);
        carriage.setAdaptModel(adapter);
        boolean update = updateById(carriage);
        if (!update) {
            return Result.RollBackError("编辑失败！");
        }

        // 判断是否有必要修改座位表
        Integer oldLeft = car.getLeftSeat();
        Integer oldRight = car.getRightSeat();
        Integer oldRow = car.getRowSeat();
        if (!oldLeft.equals(sizeLeft) || !oldRight.equals(sizeRight) || !oldRow.equals(sizeRow)) {
            // 删除原来的座位
            Result err = removeSeats(id);
            if (err != null) {
                return err;
            }
            // 增加新座位
            Result error = generateSeats(sizeLeft, sizeRight, sizeRow, carriage);
            if (error != null) {
                return error;
            }
        }
        return Result.OK();
    }

    private Result removeSeats(Integer id) {
        boolean remove = iSeatService.remove(new QueryWrapper<Seat>().eq("carriage_id", id));
        if (!remove) {
            return Result.RollBackError("删除原来座位失败！");
        }
        return null;
    }

    @Transactional
    @Override
    public Result deleteCarriage(Integer id) {

        boolean remove = removeById(id);
        if (!remove) {
            return Result.RollBackError("删除失败！");
        }
        // 删除对应的座位
        Result err = removeSeats(id);
        if (err != null) {
            return err;
        }

        // 删除车次与车厢关联表
        /*TrainCarriage trainCarriage = iTrainCarriageService.getOne(new QueryWrapper<TrainCarriage>().eq("carriage_id", id));
        if (trainCarriage == null){
            return Result.RollBackError("查询车厢车次关联表失败");
        }
        // 修改车次的车厢序号
        boolean update = iTrainCarriageService.update(new UpdateWrapper<TrainCarriage>()
                .eq("train_id", trainCarriage.getTrainId())
                .gt("carriage_order", trainCarriage.getCarriageOrder())
                .setSql("carriage_order=carriage_order-1"));
        if (!update){
            return Result.RollBackError("修改车次的车厢顺序失败！");
        }
        boolean remove1 = iTrainCarriageService.remove(new QueryWrapper<TrainCarriage>().eq("carriage_id", id));
        if (!remove1){
            return Result.RollBackError("删除车次车厢关系失败！");
        }*/
        return Result.OK();
    }

    @Override
    public Result getCarriageListOnTrain() {
        List<Map<String, Object>> maps = listMaps(new QueryWrapper<Carriage>().select("id", "carriage_number"));
        return new Result(maps);
    }
}
