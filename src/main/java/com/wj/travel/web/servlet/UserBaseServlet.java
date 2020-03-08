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
 * @Date : 2020/3/8 11:11
 * 跟User相关的servlet
 **/
@WebServlet("/userBase/*")
public class UserBaseServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void registerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        boolean flag = userService.register(userBean);
        System.out.println(flag);
        ResultInfo resultInfo = new ResultInfo();
        if (flag) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }

    /**
     * 激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void activeUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        // 找到验code 如果code找不到就不提示
        if (code != null) {
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

    /**
     * 登录操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void loginServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * 登出操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logoutServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 寻找user的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findUserServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean userInfo = (UserBean) session.getAttribute("userInfo");
        // 拿到用户的名字
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getWriter(), userInfo);
    }

}
