package com.hg.pojo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class User implements Serializable {
	private static final long serialVersionUID = -4485845624665364736L;
	private Integer id;
	
	@NotBlank(message="{user.username.notblank}")
	private String username;
	@NotBlank(message="{user.password.notblank}")
	private String password;
	private String phone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
