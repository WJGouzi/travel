package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.UserDao;
import com.wj.travel.domain.UserBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Project : travel
 * @Package : com.wj.travel.dao.daoImpl
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:29
 **/
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public UserBean findUserByUsername(String username) {
        try {
            String sql = "select * from tab_user where username = ?";
            UserBean userBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), username);
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean saveUserInfo(UserBean userBean) {
        try {
            String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email) " +
                    "values(?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                                userBean.getUsername(),
                                userBean.getPassword(),
                                userBean.getName(),
                                userBean.getBirthday(),
                                userBean.getSex(),
                                userBean.getTelephone(),
                                userBean.getEmail()
                                );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
