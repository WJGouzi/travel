package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.LikeDao;
import com.wj.travel.domain.LikeBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

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

    @Override
    public Integer countWithRid(int rid) {
        try {
            String sql = "select count(*) from tab_favorite where rid = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, rid);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void addLike(int uid, int rid) {
        try {
            String sql = "insert into tab_favorite values(?, ?, ?)";
            jdbcTemplate.update(sql, rid, new Date(), uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LikeBean> findAllLikeByUid(Integer uid, Integer startIndex, Integer pageSize) {
        try {
             String sql = "select * from tab_favorite where uid = ? limit ?, ?";
             List<LikeBean> likeBeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LikeBean>(LikeBean.class), uid, startIndex, pageSize);
             return likeBeans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
