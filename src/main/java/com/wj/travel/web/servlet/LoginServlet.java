package com.wj.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.travel.domain.ResultInfo;

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
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
