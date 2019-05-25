package com.hg.service.impl;

import java.util.List;

import org.pagination.JspPagination;
import org.pagination.QueryHandler;
import org.pagination.impl.DefaultJspPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.dao.BookDao;
import com.hg.pojo.Book;
import com.hg.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public JspPagination<Book> getBookList(String name, String author, Integer pageIndex, Integer pageSize) {
		return new DefaultJspPagination<>(pageIndex, pageSize, new QueryHandler<Book>() {

			@Override
			public Long getCount() {
				return bookDao.count(name, author).longValue();
			}

			@Override
			public List<Book> getData(Integer pageIndex, Integer pageSize) {
				return bookDao.list(name, author, pageSize * (pageIndex - 1), pageSize);
			}
		});
	}
	
	@Override
	public JspPagination<Book> getBookList(String keyword, Integer pageIndex, Integer pageSize) {
		return new DefaultJspPagination<>(pageIndex, pageSize, new QueryHandler<Book>() {
			@Override
			public Long getCount() {
				return bookDao.countByKeyword(keyword).longValue();
			}

			@Override
			public List<Book> getData(Integer pageIndex, Integer pageSize) {
				return bookDao.listByKeyword(keyword, pageSize * (pageIndex - 1), pageSize);
			}
		});
	}
	@Override
	public boolean isCountIdByName(Integer id,String name) {
		if(id == null){
			return bookDao.countByName(name) > 0;
		}else{
			return bookDao.countIdByName(id, name) > 0;
		}
	}
	@Override
	public boolean save(Book book) {
		return bookDao.save(book) > 0;
	}

	@Override
	public boolean delete(Integer id) {
		if(bookDao.selectBorrow(id) < 1 && bookDao.delete(id) > 0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public boolean update(Book book) {
		return bookDao.update(book) > 0;
	}
	@Override
	public Book get(Integer id) {
		return bookDao.get(id);
	}
}
