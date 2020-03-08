package com.wj.travel.service.serviceImpl;

import com.wj.travel.dao.UserDao;
import com.wj.travel.dao.daoImpl.UserDaoImpl;
import com.wj.travel.domain.UserBean;
import com.wj.travel.service.UserService;
import com.wj.travel.utils.MailUtils;
import com.wj.travel.utils.UuidUtil;

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
        // 首先去查询数据，如果有相关的人员就不能进行保存了
        UserBean findUser = userDao.findUserByUsername(userBean.getUsername());
        if (findUser != null) {
            return false;
        }
        // 将状态设置N，code随机生成
        userBean.setStatus("N");
        userBean.setCode(UuidUtil.getUuid());
        // 如果没有找到就将数据保存到本地去
        Boolean flag = userDao.saveUserInfo(userBean);
        if (flag) {
            /// 成功才发生邮件
            String link = "<a href='http://www.wangjun.com:9090/travel/userBase/activeUserServlet?code="+ userBean.getCode() +"'>请点击进行链接进行账户的激活</a>";
            MailUtils.sendMail(userBean.getEmail(), link, "账户激活邮件");
        }
        return flag;
    }

    @Override
    public boolean activeUser(String code) {

        UserBean user = userDao.findUserByUserCode(code);
        if (user == null) {
            return false;
        }
        boolean flag = userDao.changeUserStatus(user, "Y");
        return flag;
    }

    @Override
    public UserBean login(String username, String password) {
        /// 就是查找用户
        UserBean userBean = userDao.findUserByUsernameAndPassword(username, password);
        return userBean;
    }
}
