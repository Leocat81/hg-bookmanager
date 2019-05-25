package com.hg.service;

import org.pagination.JspPagination;

import com.hg.pojo.Member;

public interface MemberService {
	public JspPagination<Member> getMemberList(String name, String phone, Integer pageIndex, Integer pageSize);
	public boolean isExistsByIdentityCard(Integer id,String identityCard);
	public boolean isExistsByPhone(Integer id,String phone);
	public boolean addMember(Member member);
	public boolean updateMember(Member member);
	public Member getMemberById(Integer id);
	public boolean deleteMember(Integer id);
}
