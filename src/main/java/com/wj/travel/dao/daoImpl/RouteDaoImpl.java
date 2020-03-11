package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.RouteDao;
import com.wj.travel.domain.PageBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
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
    public int findAllRouteCount(int cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";
        String sql = " select count(*) from tab_route where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            builder.append(" and cid = ? ");
            params.add(cid);
        }
        if (null != rname && !"".equals(rname) && !"null".equals(rname)) {
            builder.append(" and rname like ? ");
            params.add("%"+ rname +"%");
        }
        sql = builder.toString();
        int count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        return count;
    }

    @Override
    public List<RouteBean> findRouteByPage(int cid, String rname, int startIndex, int pageSize) {
        //String sql = "select * from tab_route where cid = ? limit ?, ?";
        String sql = " select * from tab_route where 1 = 1 ";
        StringBuilder builder = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            builder.append(" and cid = ? ");
            params.add(cid);
        }
        if (null != rname && !"".equals(rname) && !"null".equals(rname)) {
            builder.append(" and rname like ? ");
            params.add("%"+ rname +"%");
        }
        builder.append(" limit ?, ? ");
        params.add(startIndex);
        params.add(pageSize);
        sql = builder.toString();
        List<RouteBean> routeBeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteBean>(RouteBean.class), params.toArray());
        return routeBeans;
    }

    @Override
    public RouteBean findRouteByRid(String rid) {
        String sql = "select * from tab_route where rid = ?";
        RouteBean routeBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<RouteBean>(RouteBean.class), rid);
        return routeBean;
    }

}
