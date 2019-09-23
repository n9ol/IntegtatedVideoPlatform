package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebCommentsMapper;
import com.zzrenfeng.zhsx.model.WebComments;
import com.zzrenfeng.zhsx.service.WebCommentsService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.WebComments
 */

@Service
public class WebCommentsServiceImpl extends BaseServiceImpl<BaseMapper<WebComments>, WebComments> implements WebCommentsService {

	
	@Resource
	private WebCommentsMapper webCommentsMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebComments> webCommentsMapper) {
		super.setBaseMapper(webCommentsMapper);
	}

	@Override
	public void updateBatch(List<String> ids, String isShown) {
		Map<String, Object> hm=new HashMap<String, Object>();
		hm.put("ids", ids);
		hm.put("isShown", isShown);
		webCommentsMapper.updateBatch(hm);
	}

	@Override
	public void deleteBatch(List<String> ids) {
		webCommentsMapper.deleteBatch(ids);
	}







}
