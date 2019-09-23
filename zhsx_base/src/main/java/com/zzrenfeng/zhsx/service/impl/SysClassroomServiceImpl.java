package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysClassroomMapper;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysClassroom
 */

@Service
public class SysClassroomServiceImpl extends BaseServiceImpl<BaseMapper<SysClassroom>, SysClassroom>
		implements SysClassroomService {

	@Resource
	private SysClassroomMapper sysClassroomMapper;

	private String streamingMediaServerPort = "";

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<SysClassroom> sysClassroomMapper) {
		super.setBaseMapper(sysClassroomMapper);
	}

	@Override
	public void delbatchClassRoom(List<String> ids) {
		sysClassroomMapper.delbatchClassRoom(ids);
	}

	@Override
	public void insertBatch(List<SysClassroom> slist) {
		sysClassroomMapper.insertBatch(slist);
	}

	@Override
	public void batchUpdateState(List<String> ids, String bak) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("ids", ids);
		hm.put("bak", bak);
		sysClassroomMapper.batchUpdateState(hm);
	}

	@Override
	public List<Map<String, Object>> findAllNameAndCode(String schoolId) {
		return sysClassroomMapper.findAllNameAndCode(schoolId);
	}

	@Override
	public int tUpdateOnlineState(String classCode, String serviceIp, String onlineState) {
		SysClassroom sysClassroom = new SysClassroom();
		sysClassroom.setClassCode(classCode);
		sysClassroom.setBak1(onlineState);
		sysClassroom.setServiceIp(serviceIp);
		return sysClassroomMapper.updateOnlineState(sysClassroom);
	}

	@Override
	public SysClassroom getSysClassroom(String classCode, String serviceIp) {
		String serviceIpPort = getServiceIpPort(serviceIp);
		SysClassroom sysClassroom = new SysClassroom(classCode, serviceIpPort);
		List<SysClassroom> listSysClassroom = findSelective(sysClassroom);
		if (listSysClassroom != null && listSysClassroom.size() > 0) {
			return listSysClassroom.get(0);
		}
		return null;
	}

	private String getServiceIpPort(String serviceIp) {
		if (streamingMediaServerPort.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/commonConfig.properties");
			try {
				props.load(in);
			} catch (Exception e1) {
				streamingMediaServerPort = "5080";
			}
			if (props.isEmpty()) {
				streamingMediaServerPort = "5080";
			} else {
				streamingMediaServerPort = props.get("streaming.media.server.port").toString();
			}
		}
		String serviceIpPort = serviceIp + ":" + streamingMediaServerPort;
		return serviceIpPort.trim();
	}

	@Override
	public List<SysClassroom> findClassNameAndClassIdBySchoolId(SysClassroom sysClassroom) {
		return sysClassroomMapper.findClassroomNameAndClassroomId(sysClassroom);
	}

}
