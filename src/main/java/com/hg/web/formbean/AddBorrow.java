package com.hg.web.formbean;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class AddBorrow implements Serializable{
	private static final long serialVersionUID = -7201692318810986487L;
	@NotBlank(message="{borrow.identityCard.notblank}")
	private String identityCard;
	
	@NotEmpty(message="{borrow.bookIds.notempty}")
	private List<Integer> bookIds;
	
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public List<Integer> getBookIds() {
		return bookIds;
	}
	public void setBookIds(List<Integer> bookIds) {
		this.bookIds = bookIds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
