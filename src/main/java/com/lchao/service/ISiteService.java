package com.lchao.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lchao.common.Result;
import com.lchao.entity.Site;

public interface ISiteService extends IService<Site> {
    /**
     * 添加站点
     * @param jsonObject {siteName:成都,initials:A,abbreviation:CD,longitude:23.1,latitude:12}
     * @return result
     */
    Result addSite(JSONObject jsonObject);

    /**
     * 获取所有站点
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @param key 搜索关键词
     * @return result
     */
    Result listSite(Integer pageNum, Integer pageSize, String key);

    /**
     * 获取站点列表
     * @return result
     */
    Result getSiteList();
}
