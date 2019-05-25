package com.hg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.pojo.Member;

public interface MemberDao {
	public List<Member> list(
			@Param("name")String name,
			@Param("phone")String phone,
			@Param("begin")Integer begin,
			@Param("size")Integer size);
	public Integer count(
			@Param("name")String name,
			@Param("phone")String phone);
	public Integer countByIdentityCard(String identityCard);
	
	public Integer countIdByIdentityCard(
			@Param("id")Integer id, 
			@Param("identityCard")String identityCard);
	
	public Integer countByPhone(String phone);
	
	public Integer countIdByPhone(
			@Param("id")Integer id,
			@Param("phone")String phone);
	public Integer save(Member member);
	public Integer update(Member member);
	public Member getMemberById(Integer id);
	public int deleteMember(Integer id);
	public Integer getIdentityCard(String identityCard);
	public int selectBorrow(@Param("id")Integer id);
}
