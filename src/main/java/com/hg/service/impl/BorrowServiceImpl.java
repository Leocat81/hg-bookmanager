package com.hg.service.impl;

import java.util.Date;
import java.util.List;

import org.pagination.JspPagination;
import org.pagination.QueryHandler;
import org.pagination.impl.DefaultJspPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.dao.BookDao;
import com.hg.dao.BorrowDao;
import com.hg.dao.MemberDao;
import com.hg.pojo.Book;
import com.hg.pojo.Borrow;
import com.hg.pojo.Member;
import com.hg.service.BorrowService;

@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private BorrowDao borrowDao;
	
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private BookDao bookDao;
	
	@Override
	public JspPagination<Borrow> getBorrowList(String bookName, String memberName, Integer pageIndex,
			Integer pageSize) {
		return new DefaultJspPagination<>(pageIndex, pageSize, new QueryHandler<Borrow>() {

			@Override
			public Long getCount() {
				return borrowDao.count(bookName, memberName).longValue();
			}

			@Override
			public List<Borrow> getData(Integer pageIndex, Integer pageSize) {
				return borrowDao.list(bookName, memberName, pageIndex==1 ? pageIndex-1:pageIndex, pageSize);
			}
		});
	}

	@Override
	public boolean addBorrow(String identityCard, List<Integer> bookIds) {
		Integer id = memberDao.getIdentityCard(identityCard);
		Member member = new Member();
		member.setId(id);
		int rows = 0;
		for(Integer bookId : bookIds){
			Borrow borrow = new Borrow();
			
			Book book = new Book();
			book.setId(bookId);
			borrow.setBook(book);
			borrow.setMember(member);
			borrow.setBorrowDate(new Date());
			rows += borrowDao.save(borrow);
			bookDao.updateNumById(bookId, -1);
		}
		return rows == bookIds.size();
	}

	@Override
	public boolean returnBorrow(Integer id) {
		Integer bookId = borrowDao.getBookIdById(id);

		bookDao.updateNumById(bookId, 1);
		
		return borrowDao.update(id, new Date()) == 1;
	}

	@Override
	public boolean deleteBorrowById(Integer id) {
		return borrowDao.delete(id) == 1;
	}
}
