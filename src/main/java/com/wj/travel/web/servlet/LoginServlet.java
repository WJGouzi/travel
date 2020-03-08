package com.wj.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.travel.domain.ResultInfo;
import com.wj.travel.domain.UserBean;
import com.wj.travel.service.UserService;
import com.wj.travel.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Project : travel
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/5 22:52
 **/
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkCodeNumber = (String) session.getAttribute("checkCodeNumber");
        if (checkCodeNumber == null || !checkCodeNumber.equalsIgnoreCase(check)) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(resultInfo);
            response.getWriter().write(json);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        UserBean userBean = userService.login(username, password);
        ResultInfo resultInfo = new ResultInfo();
        if (userBean == null) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("登录失败");
        } else {
            if (userBean.getStatus().equalsIgnoreCase("y")) {
                resultInfo.setFlag(true);
                // 将用户信息存储在session中
                session.setAttribute("userInfo", userBean);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("账户尚未激活");
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
