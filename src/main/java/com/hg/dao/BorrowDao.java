package com.hg.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.pojo.Borrow;

public interface BorrowDao {
	public List<Borrow> list(
			@Param("bookName")String bookName,
			@Param("memberName")String memberName,
			@Param("begin")Integer begin,
			@Param("size")Integer size);
	public Integer count(
			@Param("bookName")String bookName,
			@Param("memberName")String memberName);
	public int save(Borrow borrow);
	public Integer getBookIdById(Integer id);
	public int update(
			@Param("id")Integer id, 
			@Param("date")Date date);
	public int delete(Integer id);
}
