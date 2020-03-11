package com.wj.travel.dao;

import com.wj.travel.domain.SellerBean;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public interface SellerDao {

    /**
     * 根据sid获得经销商的信息
     * @param sid
     * @return
     */
    SellerBean findSellerBySid(Integer sid);
}
