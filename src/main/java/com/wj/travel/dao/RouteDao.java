package com.wj.travel.dao;

import com.wj.travel.domain.RouteBean;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
public interface RouteDao {

    /**
     * 查找指定cid类型的数量
     * @param cid
     * @return
     */
    int findAllRouteCount(int cid);

    /**
     * 根据cid查找分页数据
     * @param cid
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<RouteBean> findRouteByPage(int cid, int startIndex, int pageSize);
}
