package com.wj.travel.dao;

import com.wj.travel.domain.CategoryBean;

import java.util.List;

/**
 * @Project : travel
 * @Package : com.wj.travel.dao
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:24
 **/
public interface CategoryDao {
    /**
     * 查找所有的类别
     * @return
     */
    List<CategoryBean> findAllCategory();
}
