package com.wj.travel.domain;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.domain
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class LikeBean {
    private Integer rid;
    private String date;
    private UserBean userBean;

    public LikeBean() {
    }

    public LikeBean(Integer rid, String date, UserBean userBean) {
        this.rid = rid;
        this.date = date;
        this.userBean = userBean;
    }

    @Override
    public String toString() {
        return "LikeBean{" +
                "rid=" + rid +
                ", date='" + date + '\'' +
                ", userBean=" + userBean +
                '}';
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
