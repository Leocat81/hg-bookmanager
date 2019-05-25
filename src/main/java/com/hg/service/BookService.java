package com.hg.service;

import org.pagination.JspPagination;

import com.hg.pojo.Book;

public interface BookService {
	public JspPagination<Book> getBookList(String name,String author,Integer pageIndex,Integer pageSize);
	public Book get(Integer id);
	public boolean isCountIdByName(Integer id,String name);
	public boolean save(Book book);
	public boolean delete(Integer id);
	public boolean update(Book book);
	public JspPagination<Book> getBookList(String keyword, Integer pageIndex, Integer pageSize);
}
