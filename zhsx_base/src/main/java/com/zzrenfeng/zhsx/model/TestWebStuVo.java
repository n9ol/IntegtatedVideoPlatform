package com.zzrenfeng.zhsx.model;

import java.util.List;

/**
 * 
 * @author kl
 *
 */
public class TestWebStuVo {
	
	
	private List<TestWebQuestionAnswer> answerList;
	
	
	
	public List<TestWebQuestionAnswer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<TestWebQuestionAnswer> answerList) {
		this.answerList = answerList;
	}

	
	@Override
	public String toString() {
		return "TestWebStuVo [answerList=" + answerList + "]";
	}
	
	
	
	
	
}
