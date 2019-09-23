package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TpPracticeLibrary;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 16:01:40
 * @see com.zzrenfeng.zhsx.service.TpPracticeLibrary
 */
public interface TpPracticeLibraryService extends BaseService<TpPracticeLibrary> {

	void delBatchLibrary(List<String> ids);








}
