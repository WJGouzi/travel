package com.wj.travel.service.serviceImpl;

import com.wj.travel.dao.CategoryDao;
import com.wj.travel.dao.daoImpl.CategoryDaoImpl;
import com.wj.travel.domain.CategoryBean;
import com.wj.travel.service.CategoryService;
import com.wj.travel.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Project : travel
 * @Package : com.wj.travel.service.serviceImpl
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:25
 **/
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<CategoryBean> findAllCategory() {
        /// 首先在redis去查询，如果redis没有数据才在数据库去查询
        Jedis jedis = JedisPoolUtils.getJedis();
        Set<String> categories = jedis.zrange("category", 0, -1);
        List<CategoryBean> categoryBeans = null;
        if (categories == null || categories.size() == 0) {
            categoryBeans = dao.findAllCategory();
            for (CategoryBean bean : categoryBeans) {
                jedis.zadd("category", bean.getCid(), bean.getCname());
            }
        } else {
            categoryBeans = new ArrayList<CategoryBean>();
            for (String name: categories) {
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCname(name);
                categoryBeans.add(categoryBean);
            }
        }
        return categoryBeans;
    }
}
