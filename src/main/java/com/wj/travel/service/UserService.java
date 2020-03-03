package com.wj.travel.service;

import com.wj.travel.domain.UserBean;

/**
 * @Project : travel
 * @Package : com.wj.travel.service
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:27
 **/
public interface UserService {

    /**
     * 注册用户信息
     * @param userBean
     * @return
     */
    boolean register(UserBean userBean);
}
