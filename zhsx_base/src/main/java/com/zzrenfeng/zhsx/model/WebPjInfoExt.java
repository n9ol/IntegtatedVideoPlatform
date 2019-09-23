package com.zzrenfeng.zhsx.model;

import java.util.List;

/**
 * 评估项扩展类
 * @author 田杰熠
 *
 */
public class WebPjInfoExt {
		
	/**
	 * id
	 */
	private String webPjInfoId;
	
	/**
	 * 权重
	 */
	private List<Integer> weights;
	
	
	/**
	 * 评分
	 */
	private List<Double> totals;
	
	
	/**
	 * 评估内容
	 */
	private String content;
       

	public String getWebPjInfoId() {
		return webPjInfoId;
	}


	public void setWebPjInfoId(String webPjInfoId) {
		this.webPjInfoId = webPjInfoId;
	}


	public List<Integer> getWeights() {
		return weights;
	}


	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}


	public List<Double> getTotals() {
		return totals;
	}


	public void setTotals(List<Double> totals) {
		this.totals = totals;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	

	@Override
	public String toString() {
		return "WebPjInfoExt [webPjInfoId=" + webPjInfoId + ", weights=" + weights + ", totals=" + totals + ", content="
				+ content + "]";
	}


	
	
	
	
	
	
	
}
