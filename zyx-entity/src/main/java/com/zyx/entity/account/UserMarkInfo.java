package com.zyx.entity.account;

import com.zyx.entity.BaseEntity;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by WeiMinSheng on 2016/6/15.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title UserMarkInfo.java
 */
@Table(name = "user_mark")
public class UserMarkInfo extends BaseEntity {

    @Column
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer userId;

    @Column
    @ColumnType(jdbcType = JdbcType.BIGINT)
    private Long markTime;

    @Column
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    private String markHistory;

    @Column
    @ColumnType(jdbcType = JdbcType.INTEGER)
    private Integer markCount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Long markTime) {
        this.markTime = markTime;
    }

    public String getMarkHistory() {
        return markHistory;
    }

    public void setMarkHistory(String markHistory) {
        this.markHistory = markHistory;
    }

    public Integer getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Integer markCount) {
        this.markCount = markCount;
    }
}
