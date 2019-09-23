package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.model.TouchPad;
import com.zzrenfeng.zhsx.service.TouchPadService;
import com.zzrenfeng.zhsx.mapper.TouchPadMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-06-25 11:42:53
 * @see com.zzrenfeng.zhsx.service.impl.TouchPad
 */

@Service
public class TouchPadServiceImpl extends BaseServiceImpl<BaseMapper<TouchPad>, TouchPad> implements TouchPadService {

	
	@Resource
	private TouchPadMapper touchPadMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TouchPad> touchPadMapper) {
		super.setBaseMapper(touchPadMapper);
	}
	/**
	 *  添加记录
	 */
	@Override
	public int inTouchPad(TouchPad touchPad) {
		return touchPadMapper.insert(touchPad);
	}
	
	/**
	 *  删除记录
	 */
	@Override
	public int deByKey(String string) {
		return touchPadMapper.deleteByPrimaryKey(string);
	}
	/**
	 * 查询所有的数据 
	 */
	@Override
	public List<TouchPad> findTouchPad(TouchPad touchPad) {
		return touchPadMapper.findSelective(touchPad);
	}
	@Override
	public List<String> findHandWrittenBoardCodeByClassCode(TouchPad touchPad) {
		return touchPadMapper.findHandWrittenBoardCodeByClassCode(touchPad);
	}
	@Override
	public int deBatch(List<String> classCodes) {
		Map<String, Object> schoolMap = new HashMap<String, Object>();
		schoolMap.put("classCodes", classCodes);
		return touchPadMapper.delBatch(schoolMap);
	}
	
	
	
	
	
	







}
