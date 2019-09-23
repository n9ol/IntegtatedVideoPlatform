package com.zzrenfeng.zhsx.service;


import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TouchPad;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-06-25 11:42:53
 * @see com.zzrenfeng.zhsx.service.TouchPad
 */
public interface TouchPadService extends BaseService<TouchPad> {

	int inTouchPad(TouchPad touchPad);

	int deByKey(String string);

	List<TouchPad> findTouchPad(TouchPad touchPad);
	/**
	 * 根据教室编号查询所有教室编号下面的手写板设备编号
	 * @param touchPad
	 * @return
	 */
	List<String> findHandWrittenBoardCodeByClassCode(TouchPad touchPad);
	/**
	 * 批量删除
	 * @param classCodes
	 * @return
	 */
	int deBatch(List<String> classCodes);
	
	




	


}
