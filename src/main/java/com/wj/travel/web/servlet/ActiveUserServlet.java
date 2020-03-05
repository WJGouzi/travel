package com.wj.travel.web.servlet;

import com.wj.travel.service.UserService;
import com.wj.travel.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Project : travel
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/5 21:23
 **/
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        // 找到验code 如果code找不到就不提示
        if (code != null) {
            UserService userService = new UserServiceImpl();
            boolean flag = userService.activeUser(code);
            String msg = null;
            if (flag) {
                msg = "激活成功，<a href = '" + request.getContextPath() +"/login.html'>现在就登录</a>";
            } else  {
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
