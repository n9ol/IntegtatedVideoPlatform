package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebClassDevice;
import com.zzrenfeng.zhsx.model.WebDeviceTechnician;
import com.zzrenfeng.zhsx.service.WebDeviceTechnicianService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.WebDeviceTechnicianMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-08-02 11:52:51
 * @see com.zzrenfeng.zhsx.service.impl.WebDeviceTechnician
 */

@Service
public class WebDeviceTechnicianServiceImpl extends BaseServiceImpl<BaseMapper<WebDeviceTechnician>, WebDeviceTechnician> implements WebDeviceTechnicianService {

	
	@Resource
	private WebDeviceTechnicianMapper webDeviceTechnicianMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebDeviceTechnician> webDeviceTechnicianMapper) {
		super.setBaseMapper(webDeviceTechnicianMapper);
	}

	
	/**
	 * 删除多个信息
	 */
	@Override
	public void deleteBatchByKeys(String ids) {
		if(ids!=null){
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				webDeviceTechnicianMapper.deleteByPrimaryKey(idsArr[i]);
			}
		}
	}







}
