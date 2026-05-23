package com.smd.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {
	private String href;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
