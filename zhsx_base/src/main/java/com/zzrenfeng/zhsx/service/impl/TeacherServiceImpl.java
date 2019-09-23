package com.zzrenfeng.zhsx.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysHistoryMapper;
import com.zzrenfeng.zhsx.mapper.UserMapper;
import com.zzrenfeng.zhsx.mapper.WebPjMapper;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.TeacherService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<BaseMapper<User>, User> implements TeacherService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private SysHistoryMapper sysHistoryMapper;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private UserService userService;

	@Resource
	private WebPjMapper webPjMapper;

	@Override
	public Page<Map<String, Object>> findHotTheacher(int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return userMapper.findHotTeacher(null);
	}

	@Override
	public Page<User> findNewTeacher(int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return userMapper.findNewTeacher();
	}

	@Override
	public Page<Map<String, String>> findTeacherList(Map<String, String> m, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return userMapper.findTeacherList(m);
	}

	@Override
	public Map<String, Object> findTeacherDetails(Map<String, String> param) {
		return userMapper.findTeacherDetails(param);
	}

	@Override
	public List<Map<String, Object>> findGrowthCurve(String userId) {
		return sysHistoryMapper.findGrowthCurve(userId);
	}

	@Override
	public List<Map<String, Object>> findEXPBeforeThisYear(String userId) {
		return sysHistoryMapper.findEXPBeforeThisYear(userId);
	}

	@Override
	public int addCollection(SysHistory sysHistory) {
		int re = 0;
		List<SysHistory> h = sysHistoryService.findSelective(sysHistory);
		if (h != null && h.size() > 0) {
			re = 2;
		} else {
			Date date = new Date();
			sysHistory.setCreateTime(date);
			sysHistory.setModiyTime(date);
			sysHistoryService.insert(sysHistory);
			// Page<Map<String, String>> lis =
			// userMapper.findHotTeacher(sysHistory.getPubId());
			// List<Map<String, String>> list = lis.getResult();
			// int count = 0;//关注数教师总人数
			// if(list!=null&&list.size()>0){
			// Map<String, Object> m = (Map)list.get(0);
			// String str = m.get("gzd").toString();
			// count = Integer.parseInt(str);
			// }
			// if(count==35){//关注度首次达标 奖励经验值
			SysHistory sysh = new SysHistory();
			sysh.setUserId(sysHistory.getPubId());
			sysh.setPubType(SysHistory.PUBTYPE_T);
			sysh.setPubFlag(SysHistory.PUBFLAG_H);
			sysh.setBak1("被关注一次,获得10经验值");//
			sysh.setPubId(sysHistory.getPubId());
			String exp = "10";
			// List<SysHistory> shList = sysHistoryService.findSelective(sysh);
			// if(shList==null||shList.size()==0){
			Properties props = new Properties();
			InputStream in = null;
			try {
				in = getClass().getResourceAsStream("/exp.properties");
				props.load(in);
				exp = props.get("collectionTeacher.exp").toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

			int uve = Integer.valueOf(exp);

			sysh.setBak(exp);
			sysh.setCreateTime(date);
			sysh.setModiyTime(date);
			sysHistoryService.insert(sysh);
			userService.updateUserExp(sysHistory.getPubId(), uve);
			// }
			// }
			re = 1;
		}
		return re;
	}

	@Override
	public void deleteCollectionByPub(String pubFlag, String pubType, String pubId, String currUserId) {
		SysHistory sysHistory = new SysHistory();
		sysHistory.setUserId(currUserId);
		sysHistory.setPubFlag(pubFlag);
		sysHistory.setPubType(pubType);
		sysHistory.setPubId(pubId);
		sysHistoryMapper.deleteByPub(sysHistory);

		//

		SysHistory sysh = new SysHistory();
		sysh.setUserId(sysHistory.getPubId());
		sysh.setPubType(SysHistory.PUBTYPE_T);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setBak1("关注人数减一,扣除10经验值");// (教师成长值奖励途径)A注册B关注度达标，C完善资料
		sysh.setPubId(sysHistory.getPubId());
		String exp = "-10";

		int uve = Integer.valueOf(exp);
		Date date = new Date();
		sysh.setBak(exp);
		sysh.setCreateTime(date);
		sysh.setModiyTime(date);
		sysHistoryService.insert(sysh);
		userService.updateUserExp(sysHistory.getPubId(), uve);
	}

	@Override
	public Map<String, Object> findGrowth(String userId) {
		return webPjMapper.findGrowth(userId);
	}

}
