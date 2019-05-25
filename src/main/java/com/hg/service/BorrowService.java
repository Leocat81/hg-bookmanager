package com.hg.service;

import java.util.List;

import org.pagination.JspPagination;

import com.hg.pojo.Borrow;

public interface BorrowService {
	public JspPagination<Borrow> getBorrowList(String bookName,String memberName,Integer pageIndex, Integer pageSize);

	public boolean addBorrow(String identityCard, List<Integer> bookIds);

	public boolean returnBorrow(Integer id);

	public boolean deleteBorrowById(Integer id);
}
