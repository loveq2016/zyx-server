package com.zyx.service.activity.impl;

import com.zyx.constants.Constants;
import com.zyx.constants.activity.ActivityConstants;
import com.zyx.entity.activity.PageViews;
import com.zyx.mapper.activity.ActivityTopicMapper;
import com.zyx.mapper.activity.PageViewsMapper;
import com.zyx.service.activity.PageViwesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by SubDong on 16-6-27.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title PageViwesServiceImpl
 * @package com.zyx.service.activity.impl
 * @update 16-6-27 上午11:35
 */
@Service
public class PageViwesServiceImpl implements PageViwesService {
    //浏览量
    private static String REDIS_PAGE_VIEWS = "pageViews";

    @Resource
    protected RedisTemplate<String, String> jedisTemplate;

    @Resource
    private PageViewsMapper pageViewsMapper;

    @Override
    public void pageViwes(Integer types, Integer typeId) {
        String sValue = jedisTemplate.opsForValue().get(REDIS_PAGE_VIEWS + types);
        String newValue;
        if (sValue == null) {
            newValue = typeId + "_" + types;
            jedisTemplate.opsForValue().set(REDIS_PAGE_VIEWS + types, newValue);
        } else if (!sValue.contains(typeId + "_" + types)) {
            newValue = sValue + "," + typeId + "_" + types;
            jedisTemplate.opsForValue().set(REDIS_PAGE_VIEWS + types, newValue);
        }

        String sidValue = jedisTemplate.opsForValue().get(typeId + "_" + types);
        if (sidValue != null && !sidValue.equals("")) {
            int newSidValue = Integer.parseInt(sidValue) + 1;
            jedisTemplate.opsForValue().set(typeId + "_" + types, newSidValue + "");
        } else {
            jedisTemplate.opsForValue().set(typeId + "_" + types, "1");
        }
    }

    @Override
    public Map<String,Object> getPageViwes(Integer types, Integer typeId) {
        Map<String,Object> map = new HashMap<>();
        if(typeId != null && types != null){
            PageViews pageViews = new PageViews();
            pageViews.setTypes(types);
            pageViews.setTypeId(typeId);

            PageViews views = pageViewsMapper.queryPageView(pageViews);

            String sidValue = jedisTemplate.opsForValue().get(typeId + "_" + types);

            if (views != null) {
                views.setPageviews((views.getPageviews() == null ? 0 : views.getPageviews()) + (sidValue == null ? 0 : Integer.valueOf(sidValue)));
                map.put(Constants.STATE, Constants.SUCCESS);
                map.put(ActivityConstants.AUTH_SUCCESS, views);
                return map;
            }else{
                map.put(Constants.STATE, ActivityConstants.AUTH_ERROR_10002);
                map.put(ActivityConstants.AUTH_SUCCESS, "查无数据!");
                return map;
            }
        }else{
            map.put(Constants.STATE,Constants.PARAM_MISS);
            map.put(Constants.ERROR_MSG,"参数缺失");
            return map;
        }
    }

    @Override
    public PageViews getPageViwesByInternal(Integer types, Integer typeId) {

        PageViews pageViews = new PageViews();
        pageViews.setTypes(types);
        pageViews.setTypeId(typeId);

        PageViews views = pageViewsMapper.queryPageView(pageViews);

        String sidValue = jedisTemplate.opsForValue().get(typeId + "_" + types);

        if(views != null){
            views.setPageviews((views.getPageviews() == null ? 0 : views.getPageviews()) + (sidValue == null ? 0 : Integer.valueOf(sidValue)));
        }
        return views;
    }
}