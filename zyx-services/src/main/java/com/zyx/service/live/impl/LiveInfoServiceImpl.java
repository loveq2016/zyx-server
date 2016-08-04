package com.zyx.service.live.impl;

import java.util.List;

import com.zyx.entity.deva.dto.DevaDto;
import com.zyx.entity.live.dto.LiveInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyx.entity.live.LiveInfo;
import com.zyx.mapper.live.LiveInfoMapper;
import com.zyx.service.BaseServiceImpl;
import com.zyx.service.live.LiveInfoService;
import com.zyx.vo.live.LiveInfoVo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("liveInfoService")
public class LiveInfoServiceImpl extends BaseServiceImpl<LiveInfo> implements LiveInfoService {

	@Autowired
	LiveInfoMapper liveInfoMapper ;

	public LiveInfoServiceImpl() {
		super(LiveInfo.class);
	}

	@Override
	public int countLive(LiveInfoVo vo) {
		return liveInfoMapper.countLives(vo);
	}
	@Override
	public List<LiveInfoDto> selectLives(LiveInfoVo vo) {
		return liveInfoMapper.selectLives(vo);
	}
	@Override
	public List<LiveInfo> selectLiveDevas(List<Integer> ids) {
		if(ids==null||ids.isEmpty())
			return null;
		else{
			Example example = new Example(DevaDto.class);
			example.selectProperties("id","");
			Criteria criteria = example.createCriteria();
			criteria.andIn("id", ids);
			return liveInfoMapper.selectByExample(example);
		}
	}

	@Override
	public void logicDelete(Integer id) {
		LiveInfo liveInfo = new LiveInfo();
		liveInfo.setId(id);
		liveInfo.setDel(-1);
		updateNotNull(liveInfo);
	}
}
