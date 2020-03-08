package com.wj.travel.domain;

/**
 * @Project : travel
 * @Package : com.wj.travel.domain
 * @Author : Created By wangjun, Copyright © wangjun All Rights Reserved
 * @Date : 2020/3/8 22:27
 **/
public class CategoryBean {

    private Integer cid;
    private String cname;

    public CategoryBean() {
    }

    public CategoryBean(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
