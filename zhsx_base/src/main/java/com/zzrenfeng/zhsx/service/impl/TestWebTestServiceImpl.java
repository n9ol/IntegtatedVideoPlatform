package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.TestWebTest;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.mapper.TestWebTestMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-05 15:34:13
 * @see com.zzrenfeng.zhsx.service.impl.TestWebTest
 */

@Service
public class TestWebTestServiceImpl extends BaseServiceImpl<BaseMapper<TestWebTest>, TestWebTest> implements TestWebTestService {

	
	@Resource
	private TestWebTestMapper testWebTestMapper;
	@Resource
	private UserService userService;
	@Resource
	private SysHistoryService sysHistoryService;
	/**
	 * 上传试卷经验值
	 */
	private String uploadTestExp = "";
	
	/**
	 * 每天上传试卷获取经验值上限
	 */
	private String uploadTestExpCeil = "";
	
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebTest> testWebTestMapper) {
		super.setBaseMapper(testWebTestMapper);
	}

	@Override
	public void delBatchTest(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		testWebTestMapper.delBatchTest(hm);
	}

	@Override
	public void addUserExp(String userId, String pubId) throws ParseException {
		if (uploadTestExp.isEmpty() || uploadTestExpCeil.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/exp.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				uploadTestExp = "20";
				uploadTestExpCeil = "100";
			}
			if (props.isEmpty()) {
				uploadTestExp = "20";
				uploadTestExpCeil = "100";
			} else {
				uploadTestExp = props.get("uploadTest.exp").toString();
				uploadTestExpCeil = props.get("uploadTest.exp.ceil").toString();
			}
		}
		int uce = Integer.valueOf(uploadTestExp);
		int ucec = Integer.valueOf(uploadTestExpCeil);
		
		Date date = new Date();
		SysHistory sysh = new SysHistory();
		sysh.setUserId(userId);
		sysh.setPubType(SysHistory.PUBTYPE_E);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setStartTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		sysh.setEndTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		int expsum = Integer.valueOf(sysHistoryService.getExp(sysh));
		if(expsum<ucec){
			sysh.setPubId(pubId);
			sysh.setBak(uploadTestExp);
			sysh.setBak1("发布一套试卷,"+uce+"经验值");
			sysHistoryService.insert(sysh);
			userService.updateUserExp(userId, uce);
		}
		
	}







}
