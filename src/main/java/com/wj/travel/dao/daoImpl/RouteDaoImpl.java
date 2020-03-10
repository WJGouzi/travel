package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.RouteDao;
import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/10
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findAllRouteCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, cid);
        return count;
    }

    @Override
    public List<RouteBean> findRouteByPage(int cid, int startIndex, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ?, ?";
        List<RouteBean> routeBeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteBean>(RouteBean.class), cid, startIndex, pageSize);
        return routeBeans;
    }
}
