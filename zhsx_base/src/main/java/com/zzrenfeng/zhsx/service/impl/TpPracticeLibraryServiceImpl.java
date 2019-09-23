package com.zzrenfeng.zhsx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeLibrary;
import com.zzrenfeng.zhsx.model.TpPracticeOption;
import com.zzrenfeng.zhsx.service.TpPracticeLibraryService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeLibraryMapper;
import com.zzrenfeng.zhsx.mapper.TpPracticeOptionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 16:01:40
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeLibrary
 */

@Service
public class TpPracticeLibraryServiceImpl extends BaseServiceImpl<BaseMapper<TpPracticeLibrary>, TpPracticeLibrary> implements TpPracticeLibraryService {

	
	@Resource
	private TpPracticeLibraryMapper tpPracticeLibraryMapper;
	@Resource
	private TpPracticeOptionMapper tpPracticeOptionMapper;	
	@Resource
	public void setBaseMapper(BaseMapper<TpPracticeLibrary> tpPracticeLibraryMapper) {
		super.setBaseMapper(tpPracticeLibraryMapper);
	}
	@Override
	public int insert(TpPracticeLibrary t){
		tpPracticeLibraryMapper.insert(t);
		if("C".equals(t.getType())){
			TpPracticeOption tpo = new TpPracticeOption();
			tpo.setOption("正确");
			tpo.setIsRight("Y");
			tpo.setQid(t.getId());
			tpo.setCreateId(t.getCreateId());
			tpo.setCreateTime(new Date());
			tpo.setSort("0");
			tpPracticeOptionMapper.insert(tpo);
			tpo.setOption("错误");
			tpo.setIsRight("N");
			tpPracticeOptionMapper.insert(tpo);
		}
		
		return 0;
		
	
	
	}
	@Override
	public void delBatchLibrary(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		tpPracticeLibraryMapper.delBatchLibrary(hm);
		
	}


}
