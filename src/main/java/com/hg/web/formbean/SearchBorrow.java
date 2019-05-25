package com.hg.web.formbean;

import java.io.Serializable;

public class SearchBorrow implements Serializable {
	private static final long serialVersionUID = -3693385537086795145L;
	private String bookName;
	private String memberName;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
