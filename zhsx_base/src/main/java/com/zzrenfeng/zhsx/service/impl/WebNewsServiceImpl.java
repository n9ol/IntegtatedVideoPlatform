package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebNews;
import com.zzrenfeng.zhsx.service.WebNewsService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.WebNewsMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-16 14:48:30
 * @see com.zzrenfeng.zhsx.service.impl.WebNews
 */

@Service
public class WebNewsServiceImpl extends BaseServiceImpl<BaseMapper<WebNews>, WebNews> implements WebNewsService {

	
	@Resource
	private WebNewsMapper webNewsMapper;
	
	
	@Resource
	public void setBaseMapper(BaseMapper<WebNews> webNewsMapper) {
		super.setBaseMapper(webNewsMapper);
	}

	@Override
	public Page<WebNews> findAll(WebNews n, int p, int pageSize) {
		PageHelper.startPage(p,pageSize);
		return webNewsMapper.findAll(n);
	}

	@Override
	public List<WebNews> getTop(WebNews n) {
		List<WebNews> list = webNewsMapper.getTop(n);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public void uNewsView(String id, int view) {
		WebNews news = new WebNews();
		news.setId(id);
		news.setView(view);
		webNewsMapper.updateByPrimaryKeySelective(news);
	}

	@Override
	public void renewalData(WebNews videoResources) {
		webNewsMapper.updateByPrimaryKeySelective(videoResources);
		
	}







}
