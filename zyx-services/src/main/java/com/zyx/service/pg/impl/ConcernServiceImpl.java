package com.zyx.service.pg.impl;


import com.zyx.constants.Constants;
import com.zyx.constants.pg.PgConstants;
import com.zyx.entity.activity.Activity;
import com.zyx.entity.attention.UserAttention;
import com.zyx.entity.live.LiveInfo;
import com.zyx.entity.pg.CircleItem;
import com.zyx.mapper.attention.UserAttentionMapper;
import com.zyx.param.attention.AttentionParam;
import com.zyx.service.attention.UserAttentionService;
import com.zyx.vo.account.AccountAttentionVo;
import com.zyx.vo.attention.AttentionVo;
import com.zyx.vo.pg.MyFollowVo;
import com.zyx.entity.pg.Concern;
import com.zyx.mapper.pg.ConcernMapper;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.pg.ConcernService;
import com.zyx.utils.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by XiaoWei on 2016/6/7.
 */
@Service
public class ConcernServiceImpl extends BaseServiceImpl<Concern> implements ConcernService {

    @Resource
    private ConcernMapper concernMapper;


    public ConcernServiceImpl() {
        super(Concern.class);
    }


    @Override
    public Map<String, Object> addCern(Integer userId, Integer type, String cernTitle, String content, String cernImgurl, String videoUrl, Integer visible) {
        Map<String, Object> map = new HashMap<>();
        Concern insertCern = new Concern();
        insertCern.setCreateTime(new Date().getTime());
        Optional.ofNullable(userId).ifPresent(insertCern::setUserId);
        Optional.ofNullable(type).ifPresent(insertCern::setType);
        Optional.ofNullable(cernTitle).ifPresent(insertCern::setTopicTitle);
        Optional.ofNullable(content).ifPresent(insertCern::setTopicContent);
        Optional.ofNullable(cernImgurl).ifPresent(insertCern::setImgUrl);
        Optional.ofNullable(videoUrl).ifPresent(insertCern::setVideoUrl);
        Optional.ofNullable(visible).ifPresent(insertCern::setTopicVisible);
        insertCern.setState(0);
        save(insertCern);
        return MapUtils.buildSuccessMap(Constants.SUCCESS, PgConstants.PG_ERROR_CODE_33000_MSG, null);
//        map.put(Constants.STATE, Constants.SUCCESS);
//        return map;
    }

    @Override
    public Map<String, Object> starRandom(Integer type, Integer n) {
        Map<String, Object> map = new HashMap<>();
        try {
            Optional.ofNullable(n).orElse(10);
            List<Concern> randomList = concernMapper.starRandom(type, n);
//            map.put(Constants.STATE, Constants.MSG_SUCCESS);
//            map.put(PgConstants.PG_RESULT, Optional.ofNullable(randomList).orElse(null));
//            return map;
            return MapUtils.buildSuccessMap(Constants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, Optional.ofNullable(randomList).orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
    }

    @Override
    public Map<String, Object> getMyFollowList(Integer loginUserId) {
        try {
            if (Objects.equals(loginUserId, null)) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30000, PgConstants.PG_ERROR_CODE_30000_MSG);
            }
            AttentionParam attentionParam = new AttentionParam();
            attentionParam.setFromId(loginUserId);
            List<UserAttention> attentionIds = concernMapper.getAttentionIds(loginUserId);
            List<Integer> ids = new ArrayList<>();
            if (attentionIds.size() > 0) {
                ids.addAll(attentionIds.stream().map(UserAttention::getToUserId).collect(Collectors.toList()));
            }
            List<MyFollowVo> myFollowVos = concernMapper.myFollowList(ids);
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, myFollowVos);
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }

    }

    @Override
    public List<MyFollowVo> queryMyConcernList(Integer accountId) {
        if (accountId == null) {
            return null;
        }
        return concernMapper.myConcernList(accountId);

    }

    @Override
    public Map<String, Object> starConcern(Integer max) {
        try {
            Optional.ofNullable(max).orElse(5);
            List<MyFollowVo> myFollowVos = concernMapper.starConcern(max);
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, myFollowVos);
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
    }

    @Override
    public Integer fromConcern(Integer fromId, Integer fromType, Object fromObj) {
        if (Objects.equals(fromId, null)) {
            return 0;
        }
        if (Objects.equals(fromType, null)) {
            return 0;
        }
        if (Objects.equals(fromObj, null)) {
            return 0;
        }
        switch (fromType) {
            case Constants.DYNAMIC_LIVE:
                //直播
                LiveInfo liveInfo = (LiveInfo) fromObj;
                Concern concern = new Concern();
                concern.setFromId(fromId);
                concern.setFromType(1);
                concern.setType(5);
                concern.setCreateTime(new Date().getTime());
                if (!Objects.equals(liveInfo.getBgmUrl(), null)) {
                    concern.setImgUrl(liveInfo.getBgmUrl());//设置动态图片为直播的背景图片
                }
                concern.setTopicTitle(liveInfo.getTitle());
                concern.setTopicVisible(1);
                concern.setUserId(liveInfo.getUserId());
                concern.setState(0);
                return concernMapper.insert(concern);
            case Constants.DYNAMIC_ACTIVITY:
                //活动
                Activity activity = (Activity) fromObj;
                Concern concernActivity = new Concern();
                concernActivity.setFromId(fromId);
                concernActivity.setFromType(fromType);
                concernActivity.setType(2);
                concernActivity.setCreateTime(new Date().getTime());
                if (!Objects.equals(activity.getImgUrls(), null)) {
                    concernActivity.setImgUrl(activity.getImgUrls());
                }
                concernActivity.setTopicTitle(activity.getTitle());
                concernActivity.setTopicContent(activity.getDescContent());
                concernActivity.setTopicVisible(1);
                concernActivity.setUserId(activity.getUserId());
                concernActivity.setState(0);
                return concernMapper.insert(concernActivity);
            case Constants.DYNAMIC_CIRCLE:
                //帖子
                CircleItem circleItem = (CircleItem) fromObj;
                Concern concernItem = new Concern();
                concernItem.setFromId(fromId);
                concernItem.setFromType(fromType);
                concernItem.setType(6);
                concernItem.setCreateTime(new Date().getTime());
                concernItem.setTopicTitle(circleItem.getTitle());
                concernItem.setTopicContent(circleItem.getContent());
                concernItem.setTopicVisible(1);
                concernItem.setUserId(circleItem.getCreateId());
                concernItem.setState(0);
                return concernMapper.insert(concernItem);
        }
        return 0;
    }

    @Override
    public Map<String, Object> getOne(Integer concernId) {
        try {
            if (Objects.equals(concernId, null)) {
                return MapUtils.buildErrorMap(PgConstants.PG_ERROR_CODE_30021, PgConstants.PG_ERROR_CODE_30021_MSG);
            }
            MyFollowVo myFollowVo = concernMapper.getOne(concernId);
            return MapUtils.buildSuccessMap(PgConstants.SUCCESS, PgConstants.PG_ERROR_CODE_34000_MSG, myFollowVo);
        } catch (Exception e) {
            e.printStackTrace();
            return PgConstants.MAP_500;
        }
    }

}