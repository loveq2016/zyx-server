package com.zyx.rpc.live.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zyx.entity.live.LiveInfo;
import com.zyx.rpc.live.LiveInfoFacade;
import com.zyx.service.live.LiveInfoService;
import com.zyx.vo.live.LiveInfoVo;
import com.zyx.vo.live.LiveSearchVo;

@Service("liveInfoFacade")
public class LiveInfoFacadeImpl implements LiveInfoFacade {

	@Autowired
	LiveInfoService liveInfoService;

	@Override
	public void add(LiveInfo liveInfo) {
		// 修正开始时间
		long now = System.currentTimeMillis();
		liveInfo.setStart(liveInfo.getStart() == null || liveInfo.getStart() < now ? now : liveInfo.getStart());
		liveInfoService.save(liveInfo);
	}

	@Override
	public void updateNotNull(LiveInfo liveInfo) {
		liveInfoService.updateNotNull(liveInfo);
	}

	@Override
	public LiveInfo getById(Long id) {
		return liveInfoService.selectByKey(id);
	}

	@Override
	public List<LiveInfo> getList(LiveInfoVo liveInfoVo) {
		if (liveInfoVo.getPageNo() != null && (liveInfoVo.getPageSize() == null || liveInfoVo.getPageSize() < 1)) {
			liveInfoVo.setPageSize(6);
		}
		int count = liveInfoService.countLive(liveInfoVo);
		if (count == 0)
			return null;
		else {
			liveInfoVo.setCount(count);
			return liveInfoService.selectLives(liveInfoVo);
		}
	}

	@Override
	public void delete(Long id) {
		liveInfoService.delete(id);
	}

	@Override
	public List<LiveInfo> searchList(LiveSearchVo liveSearchVo) {
		return null;
	}

	@Override
	public String getLiveUrl(Long liveId) {
		LiveInfo liveInfo = liveInfoService.selectByKey(liveId);
		return 	 liveInfo==null ?null:liveInfo.getVedioUrl();
	}

}
