package com.hg.web.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hg.pojo.User;
import com.hg.service.UserService;
import com.hg.web.interceptor.NoCheckLogin;
import com.hg.web.util.Constants;

@Controller
@RequestMapping("/")
@NoCheckLogin
public class LoginController {
	
	@Autowired
	private UserService userService;

	//导航到登录页面
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(User user) throws Exception {
		return "login";
	}

	//登录
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(@Valid User user, Errors errors, Model model, HttpSession session) throws Exception {
		if (errors.hasErrors()) {
			return "login";
		}
		
		User loginUser = userService.getUser(user.getUsername());
		if (loginUser == null) {
			errors.rejectValue("username", "user.username.notexists", "用户名不存在");
		} else if (loginUser.getPassword().equals(user.getPassword())) {
			session.setAttribute(Constants.LOGIN_USER, loginUser);
		} else {
			errors.rejectValue("password", "user.password.error", "密码错误");
		}

		if (errors.hasErrors()) {
			return "login";
		}
		
		return "redirect:/home";
	}
	//导航到注册
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(User user){
		return "register";
	}
	//注册
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(@Valid User user,Errors errors,Model model) throws Exception{
		if(errors.hasErrors()){
			return "register";
		}
		if(user.getUsername().equals("admin")){
			throw new Exception("admin不允许注册！");
		}
		userService.save(user);
		
		return "redirect:/login";
	}
}
