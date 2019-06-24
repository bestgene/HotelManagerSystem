package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.Vip;
import com.woniuxy.pojo.telpojo;

public interface UserService {
	public List<User> allUser();
	public void addUser(User user);
	public User findUserByUid(User user);
	public void register(User user);
	
	public telpojo findbyuseracc(telpojo telpojo);
	
	public void telregister(telpojo telpojo);
	
	public User findUserIdByacc(User user);
	
	public Vip findLevelIdByuserId(User user);
	
	public Level findQuatoBylevelId(Vip vip);
	
	
}
