package com.wj.travel.dao.daoImpl;

import com.wj.travel.dao.UserDao;
import com.wj.travel.domain.UserBean;
import com.wj.travel.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

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
            //UserBean userBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), username);
            List<UserBean> userbeans = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), username);
            if (userbeans.size() > 0) {
                return userbeans.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean saveUserInfo(UserBean userBean) {
        try {
            String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                                userBean.getUsername(),
                                userBean.getPassword(),
                                userBean.getName(),
                                userBean.getBirthday(),
                                userBean.getSex(),
                                userBean.getTelephone(),
                                userBean.getEmail(),
                                userBean.getStatus(),
                                userBean.getCode()
                                );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserBean findUserByUserCode(String code) {
        try {
            String sql = "select * from tab_user where code = ?";
            UserBean userBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), code);
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //SELECT * FROM tab_user WHERE code = 'ff61d8160c434883afb06f7f40b48e01'
    @Override
    public boolean changeUserStatus(UserBean user, String status) {
        try {
            String sql = "update tab_user set status = ? where code = ?";
            jdbcTemplate.update(sql, status, user.getCode());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserBean findUserByUsernameAndPassword(String username, String password) {

        try {
            String sql = "select * from tab_user where username = ? and password = ?";
            UserBean userBean = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class), username, password);
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
