package com.gentics.spring.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Greeting implements Serializable {

	private final long id;

	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}