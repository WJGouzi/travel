package com.wj.travel.dao;

import com.wj.travel.domain.LikeBean;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.dao
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public interface LikeDao {
    /**
     * 根据uid和rid查询
     * @param uid
     * @param rid
     * @return
     */
    LikeBean queryLikeState(int uid, int rid);
}
