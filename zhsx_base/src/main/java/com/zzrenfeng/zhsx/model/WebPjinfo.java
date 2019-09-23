package com.zzrenfeng.zhsx.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-23 09:27:09
 * @see com.zzrenfeng.zhsx.model.WebPjinfo
 */

public class WebPjinfo {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 评估人id
	 */
	private java.lang.String userId;
	/**
	 * 评估对象id
	 */
	private java.lang.String pgId;
	/**
	 * 评估项id
	 */
	private java.lang.String pgPjInfoId;
	/**
	 * 在线离线( ON 在线 ，OFF 离线 ）
	 */
	private java.lang.String onOff;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 权重
	 */
	private java.math.BigDecimal weight;
	/**
	 * 得分
	 */
	private java.math.BigDecimal total;
	/**
	 * 评估内容
	 */
	private java.lang.String content;
	/**
	 * 序号
	 */
	private java.lang.String bak;
	/**
	 * 评估类型 （F 课前 I 课中 A 课后）
	 */
	private java.lang.String bak1;
	/**
	 * webPjId (web_pj 表的主键id)
	 */
	private java.lang.String bak2;
	// columns END 数据库字段结束

	// 关联字段

	/**
	 * 平均分
	 */
	private double pjinfoavg;

	/**
	 * 评估人ids
	 */
	private List<String> userIds;
	private List<String> webPjIds;

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public List<String> getWebPjIds() {
		return webPjIds;
	}

	public void setWebPjIds(List<String> webPjIds) {
		this.webPjIds = webPjIds;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public double getPjinfoavg() {
		return pjinfoavg;
	}

	public void setPjinfoavg(double pjinfoavg) {
		this.pjinfoavg = pjinfoavg;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userId = value;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setPgId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.pgId = value;
	}

	public java.lang.String getPgId() {
		return this.pgId;
	}

	public void setPgPjInfoId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.pgPjInfoId = value;
	}

	public java.lang.String getPgPjInfoId() {
		return this.pgPjInfoId;
	}

	public void setOnOff(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.onOff = value;
	}

	public java.lang.String getOnOff() {
		return this.onOff;
	}

	public void setTitle(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.title = value;
	}

	public java.lang.String getTitle() {
		return this.title;
	}

	public void setWeight(java.math.BigDecimal value) {
		this.weight = value;
	}

	public java.math.BigDecimal getWeight() {
		return this.weight;
	}

	public void setTotal(java.math.BigDecimal value) {
		this.total = value;
	}

	public java.math.BigDecimal getTotal() {
		return this.total;
	}

	public void setContent(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.content = value;
	}

	public java.lang.String getContent() {
		return this.content;
	}

	public void setBak(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak = value;
	}

	public java.lang.String getBak() {
		return this.bak;
	}

	public void setBak1(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak1 = value;
	}

	public java.lang.String getBak1() {
		return this.bak1;
	}

	public void setBak2(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak2 = value;
	}

	public java.lang.String getBak2() {
		return this.bak2;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("评估人id[").append(getUserId())
				.append("],").append("评估对象id[").append(getPgId()).append("],").append("评估项id[").append(getPgPjInfoId())
				.append("],").append("在线离线( ON 在线 ，OFF 离线 ）[").append(getOnOff()).append("],").append("标题[")
				.append(getTitle()).append("],").append("权重[").append(getWeight()).append("],").append("得分[")
				.append(getTotal()).append("],").append("评估内容[").append(getContent()).append("],").append("序号[")
				.append(getBak()).append("],").append("备用[").append(getBak1()).append("],").append("备用[")
				.append(getBak2()).append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WebPjinfo == false)
			return false;
		if (this == obj)
			return true;
		WebPjinfo other = (WebPjinfo) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public WebPjinfo() {
	}

	public WebPjinfo(String bak2) {
		super();
		this.bak2 = bak2;
	}

	public WebPjinfo(String userId, String pgId, String onOff, String bak1, String bak2) {
		super();
		this.userId = userId;
		this.pgId = pgId;
		this.onOff = onOff;
		this.bak1 = bak1;
		this.bak2 = bak2;
	}

}
