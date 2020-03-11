package com.wj.travel.dao;

import com.wj.travel.domain.RouteImageBean;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public interface RouteImageDao {

    /**
     * 根据rid获取image的图片列表
     * @param rid
     * @return
     */
    List<RouteImageBean> findRouteImageByRid(String rid);
}
