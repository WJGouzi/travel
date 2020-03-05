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

}
