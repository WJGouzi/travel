package com.wj.travel.service;

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
}
