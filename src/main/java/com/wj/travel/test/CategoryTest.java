package com.wj.travel.test;

import com.wj.travel.dao.CategoryDao;
import com.wj.travel.dao.daoImpl.CategoryDaoImpl;
import com.wj.travel.domain.CategoryBean;
import com.wj.travel.service.CategoryService;
import com.wj.travel.service.serviceImpl.CategoryServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @Project : travel
 * @Package : com.wj.travel.test
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:38
 * 类别的test类
 **/
public class CategoryTest {

    private CategoryService service = new CategoryServiceImpl();
    private CategoryDao dao = new CategoryDaoImpl();

    @Test
    public void findAll() {
        List<CategoryBean> allCategory = service.findAllCategory();
        System.out.println(allCategory);
    }
}
