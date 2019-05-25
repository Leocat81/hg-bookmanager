package com.hg.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hg.pojo.Book;
import com.hg.pojo.Book.AddBookGroupSequence;
import com.hg.service.BookService;
import com.hg.service.PublishingService;
import com.hg.web.util.WebUtil;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	PublishingService publishingService;
	
	@RequestMapping(value="bookList",method=RequestMethod.GET)
	public String bookList(
			@RequestParam(required=false,defaultValue="0") Integer pageIndex,
			@RequestParam(required=false,defaultValue="3") Integer pageSize,Book book, Model model)throws Exception{
		model.addAttribute("pagination", bookService.getBookList(book.getName(), book.getAuthor(), pageIndex, pageSize));
//		model.addAttribute("msg", WebUtil.getSession("msg"));
//		WebUtil.removeSession("msg");
		return "bookList";
	}
	
	@RequestMapping(value="addBook",method=RequestMethod.GET)
	public String addBook(Book book,HttpSession session)throws Exception{
		session.setAttribute("publishing",publishingService.getPublishing());
		return "addBook";
	}
	@RequestMapping(value="checkExistsByName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> checkExistsByName(Integer id,String name)throws Exception{
			Map<String,Boolean> map = new HashMap<>();
			Boolean flag = bookService.isCountIdByName(id,name);
			map.put("valid", !flag);
			return map;
	}
	@RequestMapping(value="addBook",method=RequestMethod.POST)
	public String addBook(@Validated(AddBookGroupSequence.class) Book book,Errors errors)throws Exception{
		if(errors.hasErrors()){
			return "addBook";
		}
		if(bookService.isCountIdByName(null,book.getName())){
			errors.rejectValue("name", "book.name.exists","图书名称已经存在！");
			return "addBook";
		}
		if(errors.hasErrors()){
			return "addBook";
		}
		boolean flag = bookService.save(book);
		if(flag){
			WebUtil.setSession("msg", "图书添加成功！");
			return "redirect:/book/bookList";
		}else{
			WebUtil.setRequest("msg", "图书添加失败！");
			return "addBook";
		}
	}
//	@GetMapping(value="deleteBook")
	@RequestMapping(value="deleteBook",method=RequestMethod.GET)
	public String deleteBook(Book book)throws Exception{
		boolean flag = bookService.delete(book.getId());
		if(flag){
			WebUtil.setSession("msg", "图书删除成功！");
		}else{
			WebUtil.setSession("msg", "该图书存在尚未归还记录不允许删除！");
		}
		return "redirect:/book/bookList";
	}
//	@GetMapping(value="updateBook")
	@RequestMapping(value="updateBook",method=RequestMethod.GET)
	public String updateBook(Book book)throws Exception{
		WebUtil.setSession("publishings",publishingService.getPublishing());
		Book currBook = bookService.get(book.getId()); 
		BeanUtils.copyProperties(book, currBook);
		return "updateBook";
	}
//	@PostMapping(value="updateBook")
	@RequestMapping(value="updateBook",method=RequestMethod.POST)
	public String updateBook(@Validated(AddBookGroupSequence.class) Book book,Errors errors)throws Exception{
		if(errors.hasErrors()){
			return "updateBook";
		}
		if(bookService.isCountIdByName(book.getId(),book.getName())){
			errors.rejectValue("name", "book.name.exists","图书名称已经存在！");
			return "updateBook";
		}
		if(errors.hasErrors()){
			return "updateBook";
		}
		boolean flag = bookService.update(book);
		if(flag){
			WebUtil.setSession("msg", "图书修改成功！");
			return "redirect:/book/bookList";
		}else{
			WebUtil.setRequest("msg", "图书修改失败！");
			return "updateBook";
		}
	}
}
