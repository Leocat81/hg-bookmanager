package com.hg.pojo;

import java.io.Serializable;

public class Publishing implements Serializable {
	private static final long serialVersionUID = 5236255093944296357L;
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
