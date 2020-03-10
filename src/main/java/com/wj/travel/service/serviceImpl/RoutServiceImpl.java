package com.wj.travel.service.serviceImpl;

import com.wj.travel.dao.RouteDao;
import com.wj.travel.dao.daoImpl.RouteDaoImpl;
import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.service.RouteService;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.service.serviceImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
public class RoutServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    @Override
    public PageBean<RouteBean> findRoute(String cid, String currentPage, String pageSize) {
        int _cid = Integer.parseInt(cid);
        int allCount = routeDao.findAllRouteCount(_cid);
        PageBean<RouteBean> pageBean = new PageBean<RouteBean>();
        int _currentPage = Integer.parseInt(currentPage);
        pageBean.setTotalCount(allCount);
        int _pageSize = Integer.parseInt(pageSize);
        int totalPage = new Double(Math.ceil(new Double(allCount) / new Double(_pageSize))).intValue();
        if (_currentPage <= 0) {
            _currentPage = 1;
        }
        if (_currentPage > totalPage) {
            _currentPage = totalPage;
        }
        pageBean.setCurrentPage(_currentPage);
        pageBean.setTotalPage(totalPage);
        pageBean.setNumber(_pageSize);

        int startIndex = (_currentPage - 1) * _pageSize;
        List<RouteBean> list = routeDao.findRouteByPage(_cid, startIndex, _pageSize);
        pageBean.setBeanList(list);

        return pageBean;
    }
}
