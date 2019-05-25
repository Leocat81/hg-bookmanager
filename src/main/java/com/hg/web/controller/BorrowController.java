package com.hg.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.pagination.JspPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hg.pojo.Book;
import com.hg.service.BookService;
import com.hg.service.BorrowService;
import com.hg.service.MemberService;
import com.hg.web.formbean.AddBorrow;
import com.hg.web.formbean.SearchBorrow;
import com.hg.web.util.WebUtil;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	@Qualifier(value="validationMessageSource")
	private AbstractMessageSource messageSource;
	
	//导航到借阅页面并加载所有的借阅信息
	@RequestMapping(value="borrowList", method=RequestMethod.GET)
	public String borrowList(
		Integer pageIndex, 
		Integer pageSize, 
		SearchBorrow searchBorrow, Model model) throws Exception {
		System.out.println("dasdasd");
		pageIndex = pageIndex == null ? 1 : pageIndex;
		pageSize = pageSize == null ? 2 : pageSize;
		
		model.addAttribute("pagination", borrowService.getBorrowList(searchBorrow.getBookName(), searchBorrow.getMemberName(), pageIndex, pageSize));
//		model.addAttribute("msg", WebUtil.getSession("msg"));
//		WebUtil.removeSession("msg");
		return "borrowList";
	}
	//导航到添加借阅
	@RequestMapping(value="addBorrow",method=RequestMethod.GET)
	public String addBorrow(AddBorrow AddBorrow)throws Exception{
		return "addBorrow";
	}
		
		//JSON返回图书分页数据
		@RequestMapping(value="bookList", method=RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> bookList(
				@RequestParam(required=false, defaultValue="1") Integer pageIndex, 
				@RequestParam(required=false, defaultValue="3") Integer pageSize,
				String keyword) throws Exception {
			Map<String, Object> map = new HashMap<>();
			
			JspPagination<Book> pagination = bookService.getBookList(keyword, pageIndex, pageSize);
			
			map.put("pageList", pagination.getPageList());
			if (pagination.getIsFirst()) {
				map.put("isFirst", true);
			} else {
				map.put("isFirst", false);
				map.put("previousIndex", pagination.getPreviousIndex());
			}
			if (pagination.getIsLast()) {
				map.put("isLast", true);
			} else {
				map.put("isLast", false);
				map.put("nextIndex", pagination.getNextIndex());
				map.put("totalPages", pagination.getTotalPages());
			}
			map.put("pageIndex", pagination.getPageIndex());
			map.put("pageSize", pagination.getPageSize());
			
			return map;
		}
		//ajax验证身份证是否存在
		@RequestMapping(value="checkNotExistsByIdentityCard",method=RequestMethod.GET)
		@ResponseBody
		public boolean checkNotExistsByIdentityCard(String identityCard)throws Exception{
			return memberService.isExistsByIdentityCard(null, identityCard);
		}
		//添加借阅
		@RequestMapping(value="addBorrow",method=RequestMethod.POST)
		public String addBorrow(@Valid AddBorrow addBorrow,Errors errors)throws Exception{
			if(errors.hasErrors()){
				for(FieldError fieldError : errors.getFieldErrors()){
					if(fieldError.getField().equals("bookIds")){
						String msg = messageSource.getMessage("borrow.bookIds.notempty",null,null);
						WebUtil.setRequest(msg, msg);
						break;
					}
				}
				return "addBorrow";
			}
			if(!memberService.isExistsByIdentityCard(null, addBorrow.getIdentityCard())){
				errors.rejectValue("identityCard", "borrow.identityCard.notexists","身份证号不存在");
			}
			if(errors.hasErrors()){
				return "addBorrow";
			}
			boolean flag = borrowService.addBorrow(addBorrow.getIdentityCard(),addBorrow.getBookIds());
			if(flag){
				WebUtil.setSession("msg", "借阅成功！");
				return "redirect:/borrow/borrowList";
			}
			WebUtil.setRequest("msg", "借阅失败！");
			return "addBorrow";
		}
		@RequestMapping(value="returnBorrow" ,method =RequestMethod.GET)
		public String returnBorrow(Integer id){
			boolean flag = borrowService.returnBorrow(id);
			String msg = flag ? "归还成功！":"归还失败！";
			WebUtil.setSession("msg", msg);
			return "redirect:/borrow/borrowList";
		}
		@RequestMapping(value="deleteBorrow", method=RequestMethod.GET)
		public String deleteBorrow(Integer id)throws Exception{
			boolean flag = borrowService.deleteBorrowById(id);
			String msg = "删除借阅失败！";
			if(flag){
				msg = "删除借阅成功！";
			}
			WebUtil.setSession("msg", msg);
			return "redirect:/borrow/borrowList";
		}
}
