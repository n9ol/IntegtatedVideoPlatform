package com.zzrenfeng.zhsx.model;

/**
 * 移动端接口 - 返回数据 model
 * 
 * @author 田杰熠
 *
 */
public class AndroidiosModel {

	/**
	 * 是否需要登录 0 不需要, 1 需要
	 */
	private int isNeedLogin;

	/**
	 * 当前页
	 */
	private int currPage;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 总条数
	 */
	private long totalNum;

	/**
	 * 数据
	 */
	private Object data;

	// get and set
	public int getIsNeedLogin() {
		return isNeedLogin;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public void setIsNeedLogin(int isNeedLogin) {
		this.isNeedLogin = isNeedLogin;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AndroidiosModel [isNeedLogin=" + isNeedLogin + ", currPage=" + currPage + ", totalPage=" + totalPage
				+ ", data=" + data + "]";
	}

}