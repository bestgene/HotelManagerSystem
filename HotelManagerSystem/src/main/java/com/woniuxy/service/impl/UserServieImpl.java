package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.UserDAO;
import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.Vip;
import com.woniuxy.pojo.telpojo;
import com.woniuxy.service.UserService;

@Service("userService")
@Transactional		//添加失败
public class UserServieImpl implements UserService {
	
	@Resource
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<User> allUser() {
		return userDAO.allUser();
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public User findUserByUid(User user) {
		// 通过用户user_acc查询用户
		return userDAO.findUserByUid(user);
	}

	@Override
	public void register(User user) {
		userDAO.register(user);
		
	}

	@Override
	public telpojo findbyuseracc(telpojo telpojo) {
		
		return userDAO.findbyuseracc(telpojo);
	}

	@Override
	public void telregister(telpojo telpojo) {
		
		userDAO.telregister(telpojo);
		
	}

	@Override
	public User findUserIdByacc(User user) {
		// TODO Auto-generated method stub
		return userDAO.findUserIdByacc(user);
	}

	@Override
	public Vip findLevelIdByuserId(User user) {
		// TODO Auto-generated method stub
		return userDAO.findLevelIdByuserId(user);
	}

	@Override
	public Level findQuatoBylevelId(Vip vip) {
		// TODO Auto-generated method stub
		return userDAO.findQuatoBylevelId(vip);
	}

	

}
