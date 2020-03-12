package com.wj.travel.web.servlet;

import com.wj.travel.domain.*;
import com.wj.travel.service.LikeService;
import com.wj.travel.service.RouteService;
import com.wj.travel.service.serviceImpl.LikeServiceImpl;
import com.wj.travel.service.serviceImpl.RoutServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project : travelPro
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
@WebServlet("/routeBase/*")
public class RouteBaseServlet extends BaseServlet {

    private RouteService routeService = new RoutServiceImpl();
    private LikeService likeService = new LikeServiceImpl();

    /**
     * 根据cid找寻线路
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findRouteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        if (null == currentPage || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (null == cid || "".equals(cid) || "null".equals(cid)) {
            cid = "0";
        }

        String pageSize = request.getParameter("pageSize");
        if (null == pageSize || "".equals(pageSize)) {
            pageSize = "10";
        }
        PageBean<RouteBean> pageBean = routeService.findRoute(cid, rname, currentPage, pageSize);
        writeValue(pageBean, response);
    }

    /**
     * 根据rid获得线路的详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void routeDetailServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        RouteBean routeBean = routeService.getRouteDetailByRid(rid);
        writeValue(routeBean, response);
    }

    /**
     * 收藏相关的详情
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void likeDetailServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userInfo = (UserBean) session.getAttribute("userInfo");
        String uid = "";
        if (userInfo == null) {
            uid = "0";
        } else {
            uid = "" + userInfo.getUid();
        }
        String rid = request.getParameter("rid");
        boolean flag = likeService.queryLikeState(uid, rid);
        Map<String, Boolean> map = new HashMap<>();
        map.put("likeFlag", flag);
        writeValue(map, response);
    }

    /**
     * 收藏按钮的点击事件
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void likeActionServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        HttpSession session = request.getSession();
        UserBean userInfo = (UserBean) session.getAttribute("userInfo");
        String uid = "";
        if (userInfo == null) {
            return;
        } else {
            uid += userInfo.getUid();
        }
        likeService.addLike(Integer.parseInt(uid), Integer.parseInt(rid));
    }

    /**
     * 收藏列表
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void likeCollectionServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userInfo = (UserBean) session.getAttribute("userInfo");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        ResultInfo resultInfo = new ResultInfo();
        if (userInfo == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("未登陆");
        } else {
            if (null == currentPage || "".equals(currentPage)) {
                currentPage = "1";
            }
            if (null == pageSize || "".equals(pageSize)) {
                pageSize = "4";
            }
            Integer _pageSize = Integer.parseInt(pageSize);
            Integer startIndex = (Integer.parseInt(currentPage) - 1) * _pageSize;
            List<LikeBean> likeRoutes = likeService.findAllLikeCollection(userInfo.getUid(), startIndex, _pageSize);
            List<RouteBean> routeBeans = routeService.findAllRoutes(likeRoutes);
            resultInfo.setFlag(true);
            // 设置分页信息
            PageBean pageBean = new PageBean();
            pageBean.setCurrentPage(Integer.parseInt(currentPage));
            pageBean.setNumber(_pageSize);
            Integer totalCount = likeService.findAllLikeByUid(userInfo.getUid());
            pageBean.setTotalCount(totalCount);
            int totalPage = new Double(Math.ceil(new Double(totalCount) / new Double(_pageSize))).intValue();
            pageBean.setTotalPage(totalPage);
            pageBean.setBeanList(routeBeans);
            resultInfo.setData(pageBean);
        }
        writeValue(resultInfo, response);
    }
}
