package com.wj.travel.dao;

import com.wj.travel.domain.UserBean;

/**
 * @Project : travel
 * @Package : com.wj.travel.dao
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:29
 **/
public interface UserDao {

    UserBean findUserByUsername(String username);

}
