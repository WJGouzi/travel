package com.wj.travel.service.serviceImpl;

import com.wj.travel.dao.LikeDao;
import com.wj.travel.dao.RouteDao;
import com.wj.travel.dao.daoImpl.LikeDaoImpl;
import com.wj.travel.domain.LikeBean;
import com.wj.travel.domain.RouteBean;
import com.wj.travel.service.LikeService;

import java.util.List;

/**
 * @Project : travelPro
 * @Package : com.wj.travel.service.serviceImpl
 * @Author : Created By gouzi, Copyright © gouzi All Rights Reserved
 * @Date : 2020/3/11
 */
public class LikeServiceImpl implements LikeService {
    private LikeDao likeDao = new LikeDaoImpl();

    @Override
    public boolean queryLikeState(String uid, String rid) {
        LikeBean likeBean = likeDao.queryLikeState(Integer.parseInt(uid), Integer.parseInt(rid));
        if (likeBean == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addLike(int uid, int rid) {
        likeDao.addLike(uid, rid);
    }

    @Override
    public List<LikeBean> findAllLikeCollection(Integer uid, Integer startIndex, Integer pageSize) {
        List<LikeBean> routes = likeDao.findAllLikeByUid(uid, startIndex, pageSize);
        return routes;
    }

    @Override
    public Integer findAllLikeByUid(Integer uid) {
        return likeDao.findAllLikeByUid(uid);
    }
}
