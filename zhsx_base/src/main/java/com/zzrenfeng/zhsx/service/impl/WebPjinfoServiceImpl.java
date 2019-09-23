package com.zzrenfeng.zhsx.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebPjinfoMapper;
import com.zzrenfeng.zhsx.model.WebPjinfo;
import com.zzrenfeng.zhsx.service.WebPjinfoService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:27:41
 * @see com.zzrenfeng.zhsx.service.impl.WebPjinfo
 */

@Service
public class WebPjinfoServiceImpl extends BaseServiceImpl<BaseMapper<WebPjinfo>, WebPjinfo>
		implements WebPjinfoService {

	@Resource
	private WebPjinfoMapper webPjinfoMapper;

	@Resource
	public void setBaseMapper(BaseMapper<WebPjinfo> webPjinfoMapper) {
		super.setBaseMapper(webPjinfoMapper);
	}

	@Override
	public List<WebPjinfo> getPgAverageDraw(String pgId) {
		return webPjinfoMapper.getPgAverageDraw(pgId);
	}

	@Override
	public void insertorupdata(String userId, String pgId, String pgPjInfoId, String score) {
		WebPjinfo webpj = new WebPjinfo();
		webpj.setUserId(userId);
		webpj.setPgId(pgId);
		webpj.setPgPjInfoId(pgPjInfoId);
		webpj.setTotal(new BigDecimal(score));
		webpj.setOnOff("ON");
		webPjinfoMapper.insertorupdata(webpj);
	}

	@Override
	public void insertInitializeWebPjinfo(WebPjinfo webPjinfo) {
		webPjinfoMapper.insertInitializeWebPjinfo(webPjinfo);
	}

	@Override
	public List<WebPjinfo> listWebPjinfo(String webPjId) {
		return webPjinfoMapper.listWebPjinfo(webPjId);
	}

	@Override
	public List<WebPjinfo> listWebPjinfo(String currUserId, String pgId, String onOff, String pgType, String webPjId) {
		List<WebPjinfo> listWebPjinfo = listWebPjinfo(webPjId);
		if (listWebPjinfo == null || listWebPjinfo.size() <= 0) {
			WebPjinfo webPjinfo2 = new WebPjinfo(currUserId, pgId, onOff, pgType, webPjId);
			insertInitializeWebPjinfo(webPjinfo2);
			listWebPjinfo = listWebPjinfo(webPjId);
		}
		return listWebPjinfo;
	}

}
