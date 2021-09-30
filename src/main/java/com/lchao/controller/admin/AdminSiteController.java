package com.lchao.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lchao.common.Result;
import com.lchao.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/site")
public class AdminSiteController {

    @Autowired
    private ISiteService iSiteService;

    @GetMapping("/list")
    public Result listSite(Integer pageNum,Integer pageSize,@RequestParam(required = false) String key){
        return iSiteService.listSite(pageNum,pageSize,key);
    }

    @PostMapping("/add")
    public Result addSite(@RequestBody JSONObject jsonObject){
        // json {siteName:成都,initials:A,abbreviation:CD,longitude:23.1,latitude:12}
        return iSiteService.addSite(jsonObject);
    }
}
