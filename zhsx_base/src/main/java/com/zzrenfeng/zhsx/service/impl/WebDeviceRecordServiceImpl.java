package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebDeviceRecordMapper;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:27:49
 * @see com.zzrenfeng.zhsx.service.impl.WebDeviceRecord
 */

@Service
public class WebDeviceRecordServiceImpl extends BaseServiceImpl<BaseMapper<WebDeviceRecord>, WebDeviceRecord> implements WebDeviceRecordService {

	
	@Resource
	private WebDeviceRecordMapper webDeviceRecordMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebDeviceRecord> webDeviceRecordMapper) {
		super.setBaseMapper(webDeviceRecordMapper);
	}

	public int upByKeySelective(WebDeviceRecord deviceRecord) {
		return webDeviceRecordMapper.updateByPrimaryKeySelective(deviceRecord);
	}

	@Override
	public String selectEndTimeByDeviceCode(String deviceCode) {
		return webDeviceRecordMapper.selectEndTimeByDeviceCode(deviceCode);
	}

	@Override
	public void deleteBatchByKeys(String ids) {
		if(ids!=null){
			String[] idsArr = ids.split(",");
			for(int i=0;i<idsArr.length;i++){
				webDeviceRecordMapper.deleteByPrimaryKey(idsArr[i]);
			}
		}
	}

	@Override
	public void deleteAllDRecord() {
		webDeviceRecordMapper.deleteAllDRecord();
	}







}
