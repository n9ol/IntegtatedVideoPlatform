package com.zzrenfeng.zhsx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebDeviceRepairMapper;
import com.zzrenfeng.zhsx.model.WebClassDevice;
import com.zzrenfeng.zhsx.model.WebDeviceManage;
import com.zzrenfeng.zhsx.model.WebDeviceRepair;
import com.zzrenfeng.zhsx.service.WebDeviceRepairService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:28:17
 * @see com.zzrenfeng.zhsx.service.impl.WebDeviceRepair
 */

@Service
public class WebDeviceRepairServiceImpl extends BaseServiceImpl<BaseMapper<WebDeviceRepair>, WebDeviceRepair> implements WebDeviceRepairService {

	
	@Resource
	private WebDeviceRepairMapper webDeviceRepairMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebDeviceRepair> webDeviceRepairMapper) {
		super.setBaseMapper(webDeviceRepairMapper);
	}
	/**
	 * 不带分页查询
	 */
	@Override
	public List<WebDeviceRepair> selectDRByContions(Map<String, Object> paramMap) {
		
		return webDeviceRepairMapper.selectDRByContions(paramMap);
	}

	@Override
	public int dataUpdateByKeySelective(WebDeviceRepair dr) {
		return webDeviceRepairMapper.updateByPrimaryKeySelective(dr);
	}

	@Override
	public void dataInsert(WebDeviceRepair dr) {
		 webDeviceRepairMapper.dataInsert(dr);
	}
	
	/**
	 * 分页查询
	 * 
	 */
	public Page<WebDeviceRepair> findPageByMapSelective(Map<String, Object> paramMap, Integer p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webDeviceRepairMapper.selectDRByContions(paramMap);
	}
	
	
	public void deleteBatchByKeys(String ids) {
		if(ids!=null){
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				webDeviceRepairMapper.deleteByPrimaryKey(idsArr[i]);
			}
		}
	}







}
