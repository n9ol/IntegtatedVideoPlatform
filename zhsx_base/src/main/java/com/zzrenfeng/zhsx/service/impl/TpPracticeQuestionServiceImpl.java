package com.zzrenfeng.zhsx.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeLibrary;
import com.zzrenfeng.zhsx.model.TpPracticeOption;
import com.zzrenfeng.zhsx.model.TpPracticeQuestion;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;
import com.zzrenfeng.zhsx.model.WebQuestionnaireQuestion;
import com.zzrenfeng.zhsx.service.TpPracticeQuestionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeLibraryMapper;
import com.zzrenfeng.zhsx.mapper.TpPracticeOptionMapper;
import com.zzrenfeng.zhsx.mapper.TpPracticeQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-20 10:56:04
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeQuestion
 */

@Service
public class TpPracticeQuestionServiceImpl extends BaseServiceImpl<BaseMapper<TpPracticeQuestion>, TpPracticeQuestion> implements TpPracticeQuestionService {

	
	@Resource
	private TpPracticeQuestionMapper tpPracticeQuestionMapper;
	@Resource
	private TpPracticeLibraryMapper tpPracticeLibraryMapper;
	
	@Resource
	private TpPracticeOptionMapper tpPracticeOptionMapper;
	@Resource
	public void setBaseMapper(BaseMapper<TpPracticeQuestion> tpPracticeQuestionMapper) {
		super.setBaseMapper(tpPracticeQuestionMapper);
	}

	@Override
	public void saveBatBylId(List<String> lidslist, String qid, String userId) {
		for (int i = 0; lidslist!=null&&i < lidslist.size(); i++) {
		TpPracticeLibrary tpl=	tpPracticeLibraryMapper.selectByPrimaryKey(lidslist.get(i));
		TpPracticeQuestion wqq = new TpPracticeQuestion();
		 
		wqq.setPid(qid);
		wqq.setQuestion(tpl.getQuestion());
		wqq.setGradeName(tpl.getGradeName());
		wqq.setSubjectName(tpl.getSubjectName());
//		wqq.setRights(rights);
		wqq.setType(tpl.getType());
//		wqq.setOptions(options);
		wqq.setCreateId(userId);
		wqq.setCreateTime(new Date());
//		wqq.setSort((i+1)+"");

		StringBuffer op = new StringBuffer();
		StringBuffer op2 = new StringBuffer();
		StringBuffer rightOpt = new StringBuffer();
		TpPracticeOption wq= new TpPracticeOption();
		wq.setQid(tpl.getId());
		List<TpPracticeOption> opt = tpPracticeOptionMapper.findSelective(wq);
		
		if("C".equals(tpl.getType())){
			for (int j = 0; opt != null && j < opt.size(); j++) {
				char a = (char) (j + 65);
				if("正确".equals(opt.get(j).getOption())){
					op.append("A." + opt.get(j).getOption() + "<br/>");
					op2.append("A." + opt.get(j).getOption());
				}
				if("错误".equals(opt.get(j).getOption())){
					op.append("B." + opt.get(j).getOption() + "<br/>");
					op2.append("B." + opt.get(j).getOption());
				}
				if ("Y".equals(opt.get(j).getIsRight())) {
					rightOpt.append(a + ",");
				}
			}
		}else{
			for (int j = 0; opt != null && j < opt.size(); j++) {
				char a = (char) (j + 65);
				op.append(a + "." + opt.get(j).getOption() + "<br/>");
				op2.append(a + "." + opt.get(j).getOption());
				
				if("Y".equals(opt.get(j).getIsRight())){
					rightOpt.append(a + ",");
				}
			}
		}
		
		
		wqq.setOptions(op.toString());
//		webQuestionnaireLibrary.setBak(op2.toString());
		
		String str = rightOpt.toString();
		if(str.length()>0){
			wqq.setRights(str.substring(0, (str.length()-1)));
		}
	
		tpPracticeQuestionMapper.insert(wqq);
		}
		
	}


}
