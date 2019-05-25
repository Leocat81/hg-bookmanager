package com.hg.service;

import com.hg.pojo.User;

public interface UserService {
	public User getUser(String username);

	public Boolean save(User user);
}
