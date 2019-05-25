package com.hg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.dao.UserDao;
import com.hg.pojo.User;
import com.hg.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUser(String username) {
		return userDao.get(username);
	}

	@Override
	public Boolean save(User user) {
		return userDao.save(user) == 1;
	}
}
