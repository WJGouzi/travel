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

    /**
     * 计算某条线路的喜欢数量
     * @param rid
     * @return
     */
    Integer countWithRid(int rid);

    /**
     * 根据uid和rid添加喜欢的
     * @param uid
     * @param rid
     */
    void addLike(int uid, int rid);
}
