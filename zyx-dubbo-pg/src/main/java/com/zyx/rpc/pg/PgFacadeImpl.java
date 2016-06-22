package com.zyx.rpc.pg;

import com.zyx.service.pg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package com.zyx.pg
 * Create by XiaoWei on 2016/6/14
 */
@Service("pgFacade")
public class PgFacadeImpl implements PgFacade {
    @Autowired
    private CircleService circleService;
    @Autowired
    private ConcrenService concrenService;
    @Autowired
    private MeetService meetService;
    @Autowired
    private ZanService zanService;
    @Autowired
    private MyConcernService myConcernService;
    @Autowired
    private CircleItemService circleItemService;

    @Override
    public Map<String, Object> addMeet(Integer circleId, Integer accountId) {
        return meetService.addMeet(circleId, accountId);
    }

    @Override
    public Map<String, Object> insertCircle(String title, Integer createId, Integer state, Integer type, String details, String headImgUrl) {
        return circleService.insertCircle(title, createId, state, type, details, headImgUrl);
    }

    @Override
    public Map<String, Object> addCern(Integer userId, Integer type, String cernTitle, String content, String cernImgurl, String videoUrl, Integer visible) {
        return concrenService.addCern(userId, type, cernTitle, content, cernImgurl, videoUrl, visible);
    }

    @Override
    public Map<String, Object> addZan(Integer body_id, Integer body_type, Integer account_id) {
        return zanService.addZan(body_id, body_type, account_id);
    }

    @Override
    public Map<String, Object> addMyConcern(Integer concernId, Integer concern_type, Integer accountId) {
        return myConcernService.addMyConcern(concernId, concern_type, accountId);
    }

    @Override
    public Map<String, Object> circleList(Integer max) {
        return circleService.circleList(max);
    }

    @Override
    public Map<String, Object> starRandom(Integer type, Integer n) {
        return concrenService.starRandom(type, n);
    }

    @Override
    public Map<String, Object> addCircleItem(Integer circle_id, Integer create_id, String title, String content) {
        return circleItemService.addCircleItem(circle_id, create_id, title, content);
    }

    @Override
    public Map<String, Object> setMaster(Integer circle_id, Integer master_id, Integer account_id) {
        return circleService.setMaster(circle_id, master_id, account_id);
    }

}