package com.zyx.vo.system;

import com.zyx.vo.account.AccountAttentionVo;

import java.io.Serializable;

/**
 * Created by Rainbow on 2016/8/24.
 */
public class SearchCirleVo implements Serializable {

    /**
     * 圈子ID
     */
    private Integer id;
    /**
     * 圈子标题
     */
    private String title;

    /**
     * 圈子图片
     */
    private String headImgUrl;

    /**
     * 当前登录用户是否关注圈子
     */
    private boolean isAtten;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public boolean isAtten() {
        return isAtten;
    }

    public void setAtten(boolean atten) {
        isAtten = atten;
    }

}
