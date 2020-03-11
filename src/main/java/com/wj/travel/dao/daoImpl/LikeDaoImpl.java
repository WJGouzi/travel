package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.LikeDao;
import com.wj.travel.domain.LikeBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class LikeDaoImpl implements LikeDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public LikeBean queryLikeState(int uid, int rid) {
        try {
            String sql = "select * from tab_favorite where uid = ? and rid = ?";
            LikeBean likeBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<LikeBean>(LikeBean.class), uid, rid);
            return likeBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
