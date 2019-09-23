package com.zzrenfeng.zhsx.model;

import java.util.List;

/**
 * 考试中心-问题扩展类
 * @author kl
 *
 */
public class QuestionJosn {
	
	private int questionId;//题号
	
	private String qId;//问题Id
	private String questionTitle;
	private String questionType;
	private String gradeName;//问题分类 年级
	private String subjectName;//问题分类 科目
	private String stuType;//基础教育
	private int volume;//侧目
	private String version;//版本
	private String memberId;//出题人Id
	
	private String scores;//分值
	private String totalScores;//总分
	private int questions;//问题数量
	private int times;//考试时间
	private int isRight;//是否为正确答案
	private String answerId;//答案id
	
	private List<String> questionItems;//选项
	private List<String> questionAnswer;//答案选项
	private List<String> answerIdList;//选项id
	private List<Integer> listRight;//是否为正确答案

	
	
	public List<String> getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(List<String> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public List<Integer> getListRight() {
		return listRight;
	}

	public void setListRight(List<Integer> listRight) {
		this.listRight = listRight;
	}

	public List<String> getAnswerIdList() {
		return answerIdList;
	}

	public void setAnswerIdList(List<String> answerIdList) {
		this.answerIdList = answerIdList;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getStuType() {
		return stuType;
	}

	public void setStuType(String stuType) {
		this.stuType = stuType;
	}

	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTotalScores() {
		return totalScores;
	}

	public void setTotalScores(String totalScores) {
		this.totalScores = totalScores;
	}

	public int getQuestions() {
		return questions;
	}

	public void setQuestions(int questions) {
		this.questions = questions;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getIsRight() {
		return isRight;
	}

	public void setIsRight(int isRight) {
		this.isRight = isRight;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public List<String> getQuestionItems() {
		return questionItems;
	}

	public void setQuestionItems(List<String> questionItems) {
		this.questionItems = questionItems;
	}

	
}
