package com.gentics.spring.aop;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TimeMeasurement {

	private Long findTime;

	private Long createTime;

	public TimeMeasurement(Long findTime, Long createTime) {
		super();
		this.findTime = findTime;
		this.createTime = createTime;
	}

	public Long getFindTime() {
		return findTime;
	}

	public void setFindTime(Long findTime) {
		this.findTime = findTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
