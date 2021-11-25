package com.lchao.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lchao.ann.Login;
import com.lchao.common.Result;
import com.lchao.enums.AddOrUpdate;
import com.lchao.service.ICarriageService;
import com.lchao.service.ISiteService;
import com.lchao.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/train")
public class AdminTrainController {

    @Autowired
    private ITrainService iTrainService;
    @Autowired
    private ISiteService iSiteService;
    @Autowired
    private ICarriageService iCarriageService;

    @Login(userType = 2)
    @GetMapping("/list")
    public Result getTrainList(Integer pageNum, Integer pageSize) {
        return iTrainService.getTrainList(pageNum, pageSize);
    }

    @Login(userType = 2)
    @PostMapping("/add")
    public Result addTrain(@RequestBody JSONObject jsonObject) {
        // json {trainNumber:G2121,isReturn:1,type:1,startTime:2910290,trainCount:1,siteList:{{siteId:1,useTime:21},{}},carriageList:{{carriageId:1},{}}}
        jsonObject.put("addOrUpdate", AddOrUpdate.ADD.getValue());
        return iTrainService.addOrUpdateTrain(jsonObject);
    }

    @Login(userType = 2)
    @PostMapping("/update")
    public Result updateTrain(@RequestBody JSONObject jsonObject) {
        // json {trainNumber:G2121,isReturn:1,type:1,startTime:2910290,trainCount:1,siteList:{{siteId:1,useTime:21},{}},carriageList:{{carriageId:1},{}}}
        jsonObject.put("addOrUpdate", AddOrUpdate.UPDATE.getValue());
        return iTrainService.addOrUpdateTrain(jsonObject);
    }

    @GetMapping("/getSiteList")
    public Result getSiteList() {
        return iSiteService.getSiteList();
    }

    @Login(userType = 2)
    @GetMapping("/getCarriageList")
    public Result getCarriageList() {
        return iCarriageService.getCarriageListOnTrain();
    }

    @Login(userType = 2)
    @GetMapping("/getCarriageInfo")
    public Result getCarriageInfo(Integer id) {
        return iCarriageService.getCarriageInfo(id);
    }

    @Login(userType = 2)
    @GetMapping("/info")
    public Result getTrainInfo(Integer id) {
        return iTrainService.getTrainInfo(id);
    }
}
