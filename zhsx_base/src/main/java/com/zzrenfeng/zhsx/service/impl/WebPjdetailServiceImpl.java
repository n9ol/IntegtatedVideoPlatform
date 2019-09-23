package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebPjdetailMapper;
import com.zzrenfeng.zhsx.model.WebPjdetail;
import com.zzrenfeng.zhsx.service.WebPjdetailService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:28:08
 * @see com.zzrenfeng.zhsx.service.impl.WebPjdetail
 */

@Service
public class WebPjdetailServiceImpl extends BaseServiceImpl<BaseMapper<WebPjdetail>, WebPjdetail>
		implements WebPjdetailService {

	@Resource
	private WebPjdetailMapper webPjdetailMapper;

	@Resource
	public void setBaseMapper(BaseMapper<WebPjdetail> webPjdetailMapper) {
		super.setBaseMapper(webPjdetailMapper);
	}

	@Override
	public List<WebPjdetail> listWebPjdetail(WebPjdetail webPjdetail) {
		List<WebPjdetail> listWebPjdetail = webPjdetailMapper.findSelective(webPjdetail);
		if (listWebPjdetail == null || listWebPjdetail.size() <= 0) {
			webPjdetailMapper.insterPjdetail(webPjdetail);
			listWebPjdetail = findSelective(webPjdetail);
		}
		return listWebPjdetail;
	}

}
