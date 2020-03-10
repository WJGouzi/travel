package com.wj.travel.web.servlet;

import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.service.RouteService;
import com.wj.travel.service.serviceImpl.RoutServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project : travelPro
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
@WebServlet("/routeBase/*")
public class RouteBaseServlet extends BaseServlet {

    private RouteService routeService = new RoutServiceImpl();

    /**
     * 根据cid找寻线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findRouteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        if (null == currentPage || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (null == cid || "".equals(cid)) {
            cid = "1";
        }
        String pageSize = request.getParameter("pageSize");
        if (null == pageSize || "".equals(pageSize)) {
            pageSize = "10";
        }
        PageBean<RouteBean> pageBean = routeService.findRoute(cid, currentPage, pageSize);
        writeValue(pageBean, response);
    }

}
