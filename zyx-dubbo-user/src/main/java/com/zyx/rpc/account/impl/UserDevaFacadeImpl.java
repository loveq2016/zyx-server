package com.zyx.rpc.account.impl;

import com.zyx.constants.account.AccountConstants;
import com.zyx.entity.Devaluation;
import com.zyx.rpc.account.UserDevaFacade;
import com.zyx.service.account.UserDevaluationService;
import com.zyx.vo.account.AccountInfoVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by skmbg on 2016/6/29.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title UserDevaFacadeImpl.java
 */
@Service("userDevaFacade")
public class UserDevaFacadeImpl implements UserDevaFacade {
    @Autowired
    private UserDevaluationService userDevaluationService;

    @Override
    public List<AccountInfoVo> queryUserDeva() {
            return userDevaluationService.queryUserDeva();
    }

    @Override
    public Map<String, Object> insertUserDeva(Devaluation devaluation) {
        try {
            return userDevaluationService.insertUserDeva(devaluation);
        } catch (Exception e) {
            return AccountConstants.MAP_500;
        }
    }

}
