package com.wj.travel.test;

import com.wj.travel.dao.RouteDao;
import com.wj.travel.dao.daoImpl.RouteDaoImpl;
import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.service.RouteService;
import com.wj.travel.service.serviceImpl.RoutServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.test
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
public class RouteTest {
    private RouteService routeService = new RoutServiceImpl();
    private RouteDao routeDao = new RouteDaoImpl();

    @Test
    public void findRouteCount() {
        int c = routeDao.findAllRouteCount(5);
        System.out.println(c);
    }

    @Test
    public void findRouteByPage() {
        List<RouteBean> routeByPage = routeDao.findRouteByPage(5, 2, 10);
        System.out.println(routeByPage);
    }

    @Test
    public void findRouteWithService() {
        PageBean<RouteBean> pb = routeService.findRoute("5", "3", "10");
        System.out.println(pb);
    }

}
