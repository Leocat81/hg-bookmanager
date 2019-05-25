package com.hg.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Book implements Serializable {
	private static final long serialVersionUID = -6503536309308709679L;

	private Integer id;
	
	@NotBlank(message="{book.name.notblank}", groups={AddBookGroupA.class})
	@Length(max=50, message="{book.name.maxlength}", groups={AddBookGroupB.class})
	private String name;
	
	@NotBlank(message="{book.author.notblank}", groups={AddBookGroupA.class})
	@Length(max=20, message="{book.author.maxlength}", groups={AddBookGroupB.class})
	private String author;
	
	@NotNull(message="{book.price.notnull}", groups={AddBookGroupA.class})
	@Range(min=0, max=10000000, message="{book.price.range}", groups={AddBookGroupB.class})
	private Double price;
	
//	@NotNull(message="{book.publishing.notnull}", groups={AddBookGroupA.class})
	private Publishing publishing;
	
	@NotNull(message="{book.createDate.notnull}", groups={AddBookGroupA.class})
	private Date createDate;
	
	@NotNull(message="{book.num.notnull}", groups={AddBookGroupA.class})
	@Range(min=1, max=99999, message="{book.num.range}", groups={AddBookGroupB.class})
	private Integer num;
	
	@NotBlank(message="{book.summary.notblank}", groups={AddBookGroupA.class})
	@Length(max=200, message="{book.summary.maxlength}", groups={AddBookGroupB.class})
	private String summary;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Publishing getPublishing() {
		return publishing;
	}
	public void setPublishing(Publishing publishing) {
		this.publishing = publishing;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public interface AddBookGroupA {}
	public interface AddBookGroupB {}
	
	@GroupSequence({Default.class, AddBookGroupA.class, AddBookGroupB.class})
	public interface AddBookGroupSequence {}
}
