package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.User;

public interface UserService {
	public List<User> allUser();
	public void addUser(User user);
	public User findUserByUid(Integer uid);
}
