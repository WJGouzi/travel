package com.wj.travel.web.servlet;

import com.wj.travel.domain.CategoryBean;
import com.wj.travel.service.CategoryService;
import com.wj.travel.service.serviceImpl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Project : travel
 * @Package : ${PACKAGE_NAME}
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:22
 **/
@WebServlet("/categoryBase/*")
public class CategoryBaseServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查找所有的类别
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllCategoryServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryBean> categoryBeanList = service.findAllCategory();
        writeValue(categoryBeanList, response);
    }

}
