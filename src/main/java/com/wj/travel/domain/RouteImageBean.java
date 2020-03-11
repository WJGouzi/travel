package com.wj.travel.domain;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.domain
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class RouteImageBean {
    private Integer rgid;//商品图片id
    private Integer rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图

    public RouteImageBean() {
    }

    public RouteImageBean(Integer rgid, Integer rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    @Override
    public String toString() {
        return "RouteImageBean{" +
                "rgid=" + rgid +
                ", rid=" + rid +
                ", bigPic='" + bigPic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                '}';
    }

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
