package com.zyx.service.pg;

import com.zyx.entity.pg.Concern;
import com.zyx.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by XiaoWei on 2016/6/7.
 */
public interface ConcernService extends BaseService<Concern> {
    Map<String, Object> addCern(Integer userId, Integer type, String cernTitle, String content, String cernImgurl, String videoUrl, Integer visible);

    Map<String, Object> starRandom(Integer type, Integer n);

    Map<String, Object> getMyFollowList(Integer loginUserId);

    List<Concern> queryMyConcernList(Integer accountId);

    Map<String, Object> starConcern(Integer max);
}