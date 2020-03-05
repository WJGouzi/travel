package com.wj.travel.test;

import com.wj.travel.dao.UserDao;
import com.wj.travel.dao.daoImpl.UserDaoImpl;
import com.wj.travel.domain.UserBean;
import com.wj.travel.service.UserService;
import com.wj.travel.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

/**
 * @Project : travel
 * @Package : com.wj.travel.test
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/4 20:14
 * User的测试类
 **/
public class UserTest {

    private UserService userService = new UserServiceImpl();
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void findUserByUsername () {
        UserBean wj = userDao.findUserByUsername("wj");
        System.out.println(wj);
    }

    @Test
    public void saveUser() {
        UserBean userBean = new UserBean(null, "wj", "123", "wangjun", "2020-02-21", "0", "1243534122", "wj@qq.com", null, null);
        boolean flag = userService.register(userBean);
        System.out.println(flag);
    }
}
