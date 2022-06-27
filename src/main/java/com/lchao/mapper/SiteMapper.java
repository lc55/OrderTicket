package com.lchao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lchao.pojo.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SiteMapper extends BaseMapper<Site> {
}
