package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.CategoryDao;
import com.wj.travel.domain.CategoryBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Project : travel
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:24
 **/
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<CategoryBean> findAllCategory() {
        try {
            String sql = "select * from tab_category order by cid asc";
            List<CategoryBean> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CategoryBean>(CategoryBean.class));
            if (categories.size() > 0) {
                return categories;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
