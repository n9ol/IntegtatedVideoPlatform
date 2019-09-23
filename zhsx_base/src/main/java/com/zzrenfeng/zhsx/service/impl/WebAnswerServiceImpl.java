package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebAnswerMapper;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.WebAnswer;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebAnswerService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-06-05 10:15:08
 * @see com.zzrenfeng.zhsx.service.impl.WebAnswer
 */

@Service
public class WebAnswerServiceImpl extends BaseServiceImpl<BaseMapper<WebAnswer>, WebAnswer>
		implements WebAnswerService {

	@Resource
	private WebAnswerMapper webAnswerMapper;
	@Resource
	private UserService userService;
	@Resource
	private SysHistoryService sysHistoryService;
	/**
	 * 老师回答问题经验值
	 */
	private String responseDyExp = "";

	/**
	 * 老师回答问题被采纳时经验值
	 */
	private String responseDyAcceptExp = "";

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<WebAnswer> webAnswerMapper) {
		super.setBaseMapper(webAnswerMapper);
	}

	@Override
	public void updateBatch(List<String> ids, String ifShow) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		hm.put("ifShow", ifShow);
		webAnswerMapper.updateBatch(hm);

	}

	@Override
	public void deleteBatch(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		webAnswerMapper.deleteBatch(hm);

	}

	@Override
	public void updateIfBest(String id) {
		webAnswerMapper.updateIfBest(id);
	}

	@Override
	public List<WebAnswer> getNew(WebAnswer w) {
		List<WebAnswer> list = webAnswerMapper.getNew(w);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Page<WebAnswer> getResolve(WebAnswer w, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webAnswerMapper.getResolve(w);
	}

	@Override
	public Page<WebAnswer> getByQid(WebAnswer w, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webAnswerMapper.getByQid(w);
	}

	@Override
	public void deleteAnswer(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		webAnswerMapper.deleteAnswer(hm);

	}

	// 回答问题获得经验（一个问题只能获得一次 ）
	@Override
	public void addUserExp(String userId, String pubId, String type) {
		if (responseDyExp.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/exp.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				responseDyExp = "10";
				responseDyAcceptExp = "50";
			}
			if (props.isEmpty()) {
				responseDyExp = "10";
				responseDyAcceptExp = "50";
			} else {
				responseDyExp = props.get("responseDy.exp").toString();
				responseDyAcceptExp = props.get("responseDyAccept.exp").toString();
			}
		}
		int uce = Integer.valueOf(responseDyExp);
		int accept = Integer.valueOf(responseDyAcceptExp);

		Date date = new Date();
		SysHistory sysh = new SysHistory();
		sysh.setUserId(userId);
		sysh.setPubType(SysHistory.PUBTYPE_A);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setStartTime(date);
		sysh.setEndTime(date);
		sysh.setPubId(pubId);
		if (type.equals("R")) {

			sysh.setBak(responseDyExp);
			sysh.setBak1("回答问题," + uce + "经验值");
			sysHistoryService.insert(sysh);
			userService.updateUserExp(userId, uce);
		}
		;
		if (type.equals("B")) {

			sysh.setBak(responseDyAcceptExp);
			sysh.setBak1("答案被采纳," + accept + "经验值");
			sysHistoryService.insert(sysh);
			userService.updateUserExp(userId, accept);
		}
	}

}
