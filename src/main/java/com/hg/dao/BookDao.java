package com.hg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hg.pojo.Book;

public interface BookDao {
	public List<Book> list(
			@Param("name")String name,
			@Param("author")String author,
			@Param("begin")Integer begin,
			@Param("size")Integer size);
	public Integer count(
			@Param("name")String name,
			@Param("author")String author);
	public Integer countByName(String name);
	public Integer countIdByName(
			@Param("id")Integer id,
			@Param("name")String name);
	public Integer save(Book book);
	public Integer delete(Integer id);
	public Integer update(Book book);
	public Book get(Integer id);
	public Integer countByKeyword(@Param("keyword") String keyword);
	public List<Book> listByKeyword(
			@Param("keyword")String keyword, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	public void updateNumById(
			@Param("id")Integer id,
			@Param("num")Integer num);
	public int selectBorrow(@Param("id")Integer id);
}
