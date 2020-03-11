package com.wj.travel.service;

import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.service
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
public interface RouteService {
    /**
     * 根据cid查找线路
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<RouteBean> findRoute(String cid, String rname, String currentPage, String pageSize);

    /**
     * 根据rid获得线路的详情信息
     * @param rid
     * @return
     */
    RouteBean getRouteDetailByRid(String rid);
}
