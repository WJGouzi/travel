package com.wj.travel.service;

import com.wj.travel.domain.LikeBean;
import com.wj.travel.domain.RouteBean;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.service
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public interface LikeService {
    /**
     * 根据rid和uid查询点赞的情况
     * @param uid
     * @param rid
     * @return
     */
    boolean queryLikeState(String uid, String rid);

    /**
     * 添加喜欢
     * @param uid
     * @param rid
     */
    void addLike(int uid, int rid);

    /**
     * 根据uid找寻所有搜藏的信息
     * @param uid
     * @return
     */
    List<LikeBean> findAllLikeCollection(Integer uid, Integer startIndex, Integer pageSize);

    /**
     * 根据uid查询收藏的数量
     * @param uid
     * @return
     */
    Integer findAllLikeByUid(Integer uid);
}
