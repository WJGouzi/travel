package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.RouteImageDao;
import com.wj.travel.domain.RouteImageBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class RouteImageDaoImpl implements RouteImageDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImageBean> findRouteImageByRid(String rid) {
        try {
            String sql = "select * from tab_route_img where rid = ?";
            List<RouteImageBean> routeImageBeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImageBean>(RouteImageBean.class), rid);
            return routeImageBeans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
