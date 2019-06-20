package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;

public interface UserService {
	public List<User> allUser();
	public void addUser(User user);
	public User findUserByAcc(String user_acc);
	public UserInfo getInfoByUid(Integer user_id);
	public void updateInfo(UserInfo userInfo);
}
