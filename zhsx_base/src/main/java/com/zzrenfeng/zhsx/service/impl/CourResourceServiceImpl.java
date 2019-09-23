package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.CourResourceMapper;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.service.CourResourceService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:47:36
 * @see com.zzrenfeng.zhsx.service.impl.CourResource
 */

@Service
public class CourResourceServiceImpl extends BaseServiceImpl<BaseMapper<CourResource>, CourResource> implements CourResourceService {

	@Resource
	private CourResourceMapper courResourceMapper;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private UserService userService;
	
	/**
	 * 上传课件经验值
	 */
	private String uploadCourExp = "";
	
	/**
	 * 每天上传课件获取经验值上限
	 */
	private String uploadCourExpCeil = "";
	
	
	
	@Resource
	public void setBaseMapper(BaseMapper<CourResource> courResourceMapper) {
		super.setBaseMapper(courResourceMapper);
	}

	@Override
	public void batchDelCourRes(List<String> ids) {
		courResourceMapper.batchDelCourRes(ids);
	}

	@Override
	public void batchUpdataCourRes(CourResource courResource, String[] edit_id) {
		List<String> ids = Arrays.asList(edit_id);
		Map<String, Object> hm = new HashMap<>();
		hm.put("ids", ids);
		hm.put("state", courResource.getState());
		hm.put("gradeName", courResource.getGradeName());
		hm.put("subjectsName", courResource.getSubjectsName());
		hm.put("bak", courResource.getBak());
		hm.put("bak1", courResource.getBak1());
		hm.put("bak2", courResource.getBak2());
		courResourceMapper.batchUpdataCourRes(hm);
	}

	@Override
	public void addUserExp(String userId, String pubId) throws ParseException {
		if (uploadCourExp.isEmpty() || uploadCourExpCeil.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/exp.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				uploadCourExp = "10";
				uploadCourExpCeil = "100";
			}
			if (props.isEmpty()) {
				uploadCourExp = "10";
				uploadCourExpCeil = "100";
			} else {
				uploadCourExp = props.get("uploadCour.exp").toString();
				uploadCourExpCeil = props.get("uploadCour.exp.ceil").toString();
			}
		}
		int uce = Integer.valueOf(uploadCourExp);
		int ucec = Integer.valueOf(uploadCourExpCeil);
		
		Date date = new Date();
		SysHistory sysh = new SysHistory();
		sysh.setUserId(userId);
		sysh.setPubType(SysHistory.PUBTYPE_R);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setStartTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		sysh.setEndTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		int expsum = Integer.valueOf(sysHistoryService.getExp(sysh));
		if(expsum<ucec){
			sysh.setPubId(pubId);
			sysh.setBak(uploadCourExp);
			sysh.setBak1("发布一个课件资源,"+uce+"经验值");
			sysHistoryService.insert(sysh);
			userService.updateUserExp(userId, uce);
		}
	}







}
