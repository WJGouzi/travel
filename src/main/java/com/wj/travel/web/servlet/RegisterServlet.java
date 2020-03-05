package com.wj.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wj.travel.domain.ResultInfo;
import com.wj.travel.domain.UserBean;
import com.wj.travel.service.UserService;
import com.wj.travel.service.serviceImpl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project : travel
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:07
 **/
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        // 验证码的处理
        String checkCode = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkCodeNumber = (String) session.getAttribute("checkCodeNumber");
        System.out.println(checkCode + ":" + checkCodeNumber);
        session.removeAttribute("checkCodeNumber");
        if (checkCodeNumber == null || !checkCodeNumber.equalsIgnoreCase(checkCode)) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setErrorMsg("验证码错误");
            resultInfo.setFlag(false);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(resultInfo);
            response.getWriter().write(json);
            return;
        }

        // 用户信息的处理
        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
        UserBean userBean = new UserBean();
        try {
            BeanUtils.populate(userBean, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService userService = new UserServiceImpl();
        boolean flag = userService.register(userBean);
        System.out.println(flag);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");

        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
