package com.wj.travel.dao;

import com.wj.travel.domain.UserBean;

/**
 * @Project : travel
 * @Package : com.wj.travel.dao
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:29
 **/
public interface UserDao {
    /**
     * 根据username找到用户
     * @param username
     * @return
     */
    UserBean findUserByUsername(String username);

    /**
     * 保存用户的信息
     * @param userBean
     * @return
     */
    Boolean saveUserInfo(UserBean userBean);

    /**
     * 根据userCode找到用户
     * @param code
     * @return
     */
    UserBean findUserByUserCode(String code);

    /**
     * 修改用户的status
     * @param status
     * @return
     */
    boolean changeUserStatus(UserBean user, String status);

    /**
     * 根据用户名和用户密码查找用户
     * @param username
     * @param password
     * @return
     */
    UserBean findUserByUsernameAndPassword(String username, String password);
}
