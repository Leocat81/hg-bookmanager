package com.hg.service.impl;


import java.util.List;

import org.pagination.JspPagination;
import org.pagination.QueryHandler;
import org.pagination.impl.DefaultJspPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.dao.MemberDao;
import com.hg.pojo.Member;
import com.hg.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public JspPagination<Member> getMemberList(String name, String phone, Integer pageIndex, Integer pageSize) {
		return new DefaultJspPagination<>(pageIndex, pageSize, new QueryHandler<Member>() {

			@Override
			public List<Member> getData(Integer pageIndex, Integer pageSize) {
				return memberDao.list(name, phone, pageSize * (pageIndex - 1), pageSize);
			}

			@Override
			public Long getCount() {
				return memberDao.count(name, phone).longValue();
			}

		});
	}

	@Override
	public boolean isExistsByIdentityCard(Integer id, String identityCard) {
		if(id ==null){
			return memberDao.countByIdentityCard(identityCard) > 0;
		}else{
			return memberDao.countIdByIdentityCard(id,identityCard) > 0;
		}
	}

	@Override
	public boolean isExistsByPhone(Integer id, String phone) {
		if(id ==null){
			return memberDao.countByPhone(phone) > 0;
		}else{
			return memberDao.countIdByPhone(id,phone) > 0;
		}
	}

	@Override
	public boolean addMember(Member member) {
		return memberDao.save(member) == 1;
	}

	@Override
	public boolean updateMember(Member member) {
		return memberDao.update(member) == 1;
	}

	@Override
	public Member getMemberById(Integer id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public boolean deleteMember(Integer id) {
				if(memberDao.selectBorrow(id) < 1 && memberDao.deleteMember(id) == 1){
					return true;
				}else{
					return false;
				}
	}
	
}
