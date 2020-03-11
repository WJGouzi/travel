package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.SellerDao;
import com.wj.travel.domain.SellerBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public SellerBean findSellerBySid(Integer sid) {
        try {
            String sql = "select * from tab_seller where sid = ?";
            SellerBean sellerBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<SellerBean>(SellerBean.class), sid);
            return sellerBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
