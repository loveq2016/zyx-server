package com.zyx.mapper.pg;

import com.zyx.entity.attention.UserAttention;
import com.zyx.entity.pg.Concern;
import com.zyx.vo.attention.AttentionVo;
import com.zyx.vo.pg.MyFollowVo;
import com.zyx.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xw on 16-6-12.
 */
@Repository("concrenMapper")
public interface ConcernMapper extends BaseMapper<Concern> {
    List<Concern> starRandom(@Param("type") Integer type, @Param("n") Integer n);

    List<UserAttention> getAttentionIds(@Param("loginUserId") Integer logInUserId);

    List<MyFollowVo> myFollowList(@Param("ids") List<Integer> ids, @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 我的动态列表
     *
     * @param accountId
     * @return
     */
    List<MyFollowVo> myConcernList(@Param("accountId") Integer accountId);

    List<MyFollowVo> starConcern(@Param("start") Integer max, @Param("end") Integer end);

    MyFollowVo getOne(@Param("id") Integer concernId);

    Integer delConcern(@Param("id") Integer id);

}
