package com.lchao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lchao.common.Result;
import com.lchao.pojo.Site;
import com.lchao.mapper.SiteMapper;
import com.lchao.service.ISiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ISiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {

    @Override
    public Result addSite(JSONObject jsonObject) {
        if (jsonObject == null) {
            return Result.Error("jsonObject 为空");
        }
        String siteName = jsonObject.getString("siteName");
        String initials = jsonObject.getString("initials");
        String abbreviation = jsonObject.getString("abbreviation");
        String longitude = jsonObject.getString("longitude");
        String latitude = jsonObject.getString("latitude");

        if (StringUtils.isBlank(siteName)
                || StringUtils.isBlank(initials)
                || StringUtils.isBlank(abbreviation)
                || StringUtils.isBlank(longitude)
                || StringUtils.isBlank(latitude)) {
            return Result.Error("参数存在空值");
        }
        // 判断是否存在
        QueryWrapper<Site> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("site_name", siteName);
        int count = count(queryWrapper);
        if (count > 0) {
            return Result.Error(siteName + "：站点已经存在了！");
        }
        // 添加站点
        Site site = new Site();
        site.setSiteName(siteName);
        site.setInitials(initials);
        site.setAbbreviation(abbreviation);
        site.setLongitude(longitude);
        site.setLatitude(latitude);

        boolean save = save(site);
        if (!save) {
            return Result.Error("添加站点失败！");
        }
        return Result.OK("添加成功！");
    }

    @Override
    public Result listSite(Integer pageNum, Integer pageSize, String key) {
        if (pageNum == null || pageSize == null) {
            return Result.Error("分页参数存在空值！");
        }
        IPage<Map<String, Object>> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Site> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("site_name", "id");
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.like("site_name", key).or().like("initials", key).or().like("abbreviation", key);
        }
        IPage<Map<String, Object>> mapIPage = pageMaps(page, queryWrapper);
        return Result.page(mapIPage);
    }

    @Override
    public Result getSiteList() {
        List<Map<String, Object>> maps = listMaps(new QueryWrapper<Site>().select("id", "site_name"));
        return Result.OK(maps);
    }
}
