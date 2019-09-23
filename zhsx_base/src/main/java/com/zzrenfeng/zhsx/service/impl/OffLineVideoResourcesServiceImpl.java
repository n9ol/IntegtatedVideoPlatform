package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

@Service
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
	public void append(HttpServletRequest request, OffLineVideoResources offLineVideoResources)
			throws InterruptedException {
		if (offLineVideoResources.getType() == null || offLineVideoResources.getType().equals("")) {
			offLineVideoResources.setType("B");
		}
		offLineVideoResources.setUploadName("N");
		offLineVideoResources.setTranscodingState("U");
		offLineVideoResources.setProgress(0);
		offLineVideoResources.setReleaseState("Y");
		offLineVideoResources.setIsShow("Y");
		offLineVideoResources.setPageView(0);
		offLineVideoResources.setTeacherId(offLineVideoResources.getUserId());
		offLineVideoResources.setCreateTime(new Date());

		String quondamVideoPath = "/" + offLineVideoResources.getVideoPath(); // 原视频路径(转码前视频路径)
		String picPath = quondamVideoPath.substring(0, quondamVideoPath.lastIndexOf(".")) + ".jpg"; // 封面截图保存路径
		String newVideoSave = getRandomDirectory() + "/" + UUID.randomUUID().toString().replace("-", "") + ".flv"; // 视频转码后保存路径

		offLineVideoResources.setVideoPath(newVideoSave);
		offLineVideoResources.setPicPath(picPath);
		insert(offLineVideoResources);

		// 转码
		String webpath = "http://" + request.getLocalAddr() + ":" + request.getLocalPort() + "/"
				+ request.getRequestURI().split("/")[1] + "/adminOffLine/updateVideoRes";
		String _transcodingUrl = fileWebPath + "/VideoFormatConvertingServlet?path=videoRes&id="
				+ offLineVideoResources.getId() + "&filePath=" + quondamVideoPath + "&webpath=" + webpath
				+ "&newVideoSave=" + newVideoSave;
		RemoteConnectUtil.getRemoteConnect(_transcodingUrl);

		// 截图 - 转码开始后等待5秒再开始截图
		Thread.sleep(5000);
		String _url = fileWebPath + "/VideoScreenshotServlet?path=videoRes&filePath=" + newVideoSave;
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

	/**
	 * 创建文件保存目录
	 * 
	 * @return
	 */
	private String getRandomDirectory() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String a = sdf.format(new Date());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		String b = sdf1.format(new Date());
		String randomDirectory = "/" + a + "/" + b;
		return randomDirectory;
	}
}
