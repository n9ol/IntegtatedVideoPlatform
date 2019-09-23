package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.AfManager;
import com.zzrenfeng.zhsx.service.AfManagerService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.AfManagerMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.service.impl.AfManager
 */

@Service
public class AfManagerServiceImpl extends BaseServiceImpl<BaseMapper<AfManager>, AfManager> implements AfManagerService {

	
	@Resource
	private AfManagerMapper afManagerMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<AfManager> afManagerMapper) {
		super.setBaseMapper(afManagerMapper);
	}

	@Override
	public void deleteBatch(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		afManagerMapper.deleteBatch(hm);
		
	}

	@Override
	public List<AfManager> findAfSchool() {
		List<AfManager> list = afManagerMapper.findAfSchool();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public List<AfManager> findAfSchoolArea(AfManager af) {
		List<AfManager> list = afManagerMapper.findAfSchoolArea(af);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}


	@Override
	public Page<AfManager> findDefault(AfManager af, int p, int pageSize) {
		PageHelper.startPage(p,pageSize);
		return afManagerMapper.findDefault(af);
	}

	@Override
	public Page<AfManager> finddhSelect(AfManager af, int p, int pageSize) {
		PageHelper.startPage(p,pageSize);
		return afManagerMapper.finddhSelect(af);
	}

	@Override
	public List<AfManager> findCamearname(AfManager af) {
		List<AfManager> list = afManagerMapper.findCamearname(af);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}







}
