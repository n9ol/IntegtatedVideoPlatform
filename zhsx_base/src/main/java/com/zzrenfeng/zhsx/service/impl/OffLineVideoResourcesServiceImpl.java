package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.OffLineVideoResourcesMapper;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.RemoteConnectUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-08 11:23:59
 * @see com.zzrenfeng.zhsx.service.impl.OffLineVideoResources
 */

@Service("offLineVideoResourcesService")
public class OffLineVideoResourcesServiceImpl
		extends BaseServiceImpl<BaseMapper<OffLineVideoResources>, OffLineVideoResources>
		implements OffLineVideoResourcesService {

	@Resource
	private OffLineVideoResourcesMapper offLineVideoResourcesMapper;
	@Resource
	private UserService userService;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private String fileWebPath;

	/**
	 * 发布视频获得经验值
	 */
	private String uploadVideoExp = "";

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<OffLineVideoResources> offLineVideoResourcesMapper) {
		super.setBaseMapper(offLineVideoResourcesMapper);
	}

	@Override
	public void renewalData(OffLineVideoResources videoResources) {
		offLineVideoResourcesMapper.updateByPrimaryKeySelective(videoResources);
	}

	@Override
	public void batchDelVideoRes(List<String> ids) {
		offLineVideoResourcesMapper.batchDelVideoRes(ids);
	}

	@Override
	public void updateUserExp(String userId, String pubId) {
		User user = userService.findByKey(userId);
		if (user.getUserType().equals(User.userType_teachers)) {
			SysHistory sysh = new SysHistory();
			sysh.setUserId(userId);
			sysh.setPubType(SysHistory.PUBTYPE_V);
			sysh.setPubFlag(SysHistory.PUBFLAG_H);
			sysh.setPubId(pubId);
			List<SysHistory> shList = sysHistoryService.findSelective(sysh);
			if (shList == null || shList.size() == 0) {
				if (uploadVideoExp.isEmpty()) {
					Properties props = new Properties();
					InputStream in;
					in = getClass().getResourceAsStream("/exp.properties");
					try {
						props.load(in);
					} catch (Exception e) {
						uploadVideoExp = "120";
					}
					if (props.isEmpty()) {
						uploadVideoExp = "120";
					} else {
						uploadVideoExp = props.get("uploadVideo.exp").toString();
					}
				}
				int uve = Integer.valueOf(uploadVideoExp);
				sysh.setBak(uploadVideoExp);
				sysh.setBak1("发布一个视频,获得" + uve + "经验值");
				sysHistoryService.insert(sysh);
				userService.updateUserExp(userId, uve);
			}
		}
	}

	@Override
	public void updatePageView(String id) {
		offLineVideoResourcesMapper.updatePageView(id);
	}

	@Override
	public void append(OffLineVideoResources offLineVideoResources) {
		if (offLineVideoResources.getType() == null || offLineVideoResources.getType().equals("")) {
			offLineVideoResources.setType("B");
		}
		offLineVideoResources.setUploadName("N");
		offLineVideoResources.setTranscodingState("O");
		offLineVideoResources.setProgress(100);
		offLineVideoResources.setReleaseState("Y");
		offLineVideoResources.setIsShow("Y");
		offLineVideoResources.setPageView(0);
		offLineVideoResources.setTeacherId(offLineVideoResources.getUserId());
		offLineVideoResources.setCreateTime(new Date());
		String videoPath = offLineVideoResources.getVideoPath() + ".flv";
		String picPath = offLineVideoResources.getVideoPath() + ".jpg";

		offLineVideoResources.setVideoPath(videoPath);
		offLineVideoResources.setPicPath(picPath);
		insert(offLineVideoResources);
		// 截图
		String _url = fileWebPath + "/VideoScreenshotServlet?path=videoRes&filePath=" + videoPath;
		RemoteConnectUtil.getRemoteConnect(_url);
	}

	@Override
	public String findVideoPathById(String id) {
		return offLineVideoResourcesMapper.findVideoPathById(id);
	}

	@Override
	public int appendOffLineVideoResources(OffLineVideoResources offLineVideoResources) {
		return offLineVideoResourcesMapper.appendOffLineVideoResources(offLineVideoResources);
	}

	@Override
	public OffLineVideoResources getOffLineVideoResourcesByIds(List<String> ids) {
		List<OffLineVideoResources> listOffLineVideoResources = offLineVideoResourcesMapper
				.listOffLineVideoResourcesByIds(ids);
		if (listOffLineVideoResources != null && listOffLineVideoResources.size() > 0) {
			return listOffLineVideoResources.get(0);
		}
		return null;
	}

	@Override
	public List<String> listIds(String id) throws ParseException {
		List<String> ids = new ArrayList<>();
		ids.add(id);

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		String ipAndClassCode = id.substring(0, id.length() - 14);
		String dateTime = id.substring(id.length() - 14, id.length());
		String temp1 = dateTime;
		for (int i = 0; i < 5; i++) {
			Date date = df.parse(temp1);
			date.setTime(date.getTime() + 1000);
			temp1 = df.format(date);
			ids.add(ipAndClassCode + temp1);
		}

		String temp2 = dateTime;
		for (int i = 0; i < 5; i++) {
			Date date = df.parse(temp2);
			date.setTime(date.getTime() - 1000);
			temp2 = df.format(date);
			ids.add(ipAndClassCode + temp2);
		}

		return ids;
	}

	@Override
	public int reUpdateByKeySelective(OffLineVideoResources offLineVideoResources) {
		return offLineVideoResourcesMapper.updateByPrimaryKey(offLineVideoResources);
	}

}
