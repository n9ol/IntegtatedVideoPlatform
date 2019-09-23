package com.zzrenfeng.zhsx.model.liveplay;

import org.hibernate.validator.constraints.NotBlank;

public class QualityPlay {

	@NotBlank
	private String id;
	@NotBlank
	private String classCode;
	@NotBlank
	private String rtmpUrl;
	@NotBlank
	private String cip;
	@NotBlank
	private String ciptype;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getRtmpUrl() {
		return rtmpUrl;
	}

	public void setRtmpUrl(String rtmpUrl) {
		this.rtmpUrl = rtmpUrl;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getCiptype() {
		return ciptype;
	}

	public void setCiptype(String ciptype) {
		this.ciptype = ciptype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "QualityPlay [id=" + id + ", classCode=" + classCode + ", rtmpUrl=" + rtmpUrl + ", cip=" + cip
				+ ", ciptype=" + ciptype + "]";
	}

}
