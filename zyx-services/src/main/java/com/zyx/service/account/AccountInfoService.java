package com.zyx.service.account;

import com.zyx.entity.account.AccountInfo;
import com.zyx.entity.account.UserLoginParam;
import com.zyx.entity.account.param.AccountInfoParam;
import com.zyx.service.BaseService;
import com.zyx.vo.account.AccountInfoVo;

import java.util.List;

/**
 * Created by WeiMinSheng on 2016/6/13.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title AccountInfoService.java
 */
public interface AccountInfoService extends BaseService<AccountInfo> {
    /**
     * 通过手机号码查询用户数量
     *
     * @param phone
     * @return
     */
    int selectAccountByPhone(String phone);

    /**
     * 通过参数查询用户列表
     *
     * @param userLoginParam
     * @return
     */
    List<AccountInfoVo> selectAccountByParam(UserLoginParam userLoginParam);


    /**
     * 更新密码
     *
     * @param userLoginParam
     * @return
     */
    int renewpwd(UserLoginParam userLoginParam);

    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    int updateAccountByParam(AccountInfoParam param);
}
