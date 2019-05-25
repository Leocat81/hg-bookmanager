package com.hg.dao;

import com.hg.pojo.User;

public interface UserDao {
	public User get(String username);

	public int save(User user);
}
