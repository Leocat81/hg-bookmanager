package com.hg.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Member implements Serializable{
	private static final long serialVersionUID = -9132249694072966983L;

	@NotNull(message="{member.id.notnull}", groups={EditMemberGroupA.class})
	private Integer id;
	
	@NotBlank(message="{member.name.notblank}",groups={AddMemberGroupA.class,EditMemberGroupA.class})
	@Length(max=20,message="{member.name.maxlength}",groups={AddMemberGroupB.class,EditMemberGroupB.class})
	private String name;
	
	@NotBlank(message="{member.identityCard.notblank}",groups={AddMemberGroupA.class,EditMemberGroupA.class})
	@Length(min=18,max=18,message="{member.identityCard.length}",groups={AddMemberGroupB.class,EditMemberGroupB.class})
	private String identityCard;
	
	@NotBlank(message="{member.phone.notblank}",groups={AddMemberGroupA.class,EditMemberGroupA.class})
	@Length(max=15,message="{member.phone.maxlength}",groups={AddMemberGroupB.class,EditMemberGroupB.class})
	private String phone;
	private Date createDate;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public interface AddMemberGroupA{}
	public interface AddMemberGroupB{}
	
	public interface EditMemberGroupA{}
	public interface EditMemberGroupB{}

	@GroupSequence({Default.class,AddMemberGroupA.class,AddMemberGroupB.class})
	public interface AddMemberGroupSequence{}
	
	@GroupSequence({Default.class, EditMemberGroupA.class, EditMemberGroupB.class})
	public interface EditMemberGroupSequence {}
}
