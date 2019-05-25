package com.hg.pojo;

import java.io.Serializable;
import java.util.Date;

public class Borrow implements Serializable {
	private static final long serialVersionUID = -6839996498609753399L;
	private Integer id;
	private Member member;
	private Book book;
	private Date borrowDate;
	private Date returnDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", member=" + member + ", book=" + book + ", borrowDate=" + borrowDate
				+ ", returnDate=" + returnDate + "]";
	}
}
