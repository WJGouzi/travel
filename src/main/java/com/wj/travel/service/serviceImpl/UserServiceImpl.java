package com.wj.travel.service.serviceImpl;

import com.wj.travel.dao.UserDao;
import com.wj.travel.dao.daoImpl.UserDaoImpl;
import com.wj.travel.domain.UserBean;
import com.wj.travel.service.UserService;

/**
 * @Project : travel
 * @Package : com.wj.travel.service.serviceImpl
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/3 22:27
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(UserBean userBean) {

        
        return true;
    }
}
