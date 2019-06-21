package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.UserDAO;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
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
	public User findUserByAcc(String user_acc) {
		return userDAO.findUserByAcc(user_acc);
	}

	@Override
	public UserInfo getInfoByUid(Integer user_id) {
		return userDAO.getInfoByUid(user_id);
	}

	@Override
	public void updateInfo(UserInfo userInfo) {
		userDAO.updateInfo(userInfo);
		
	}

	@Override
	public Role selectRoleAndPerms(Integer role_id) {
		return userDAO.selectRoleAndPerms(role_id);
	}

	@Override
	public void deleteUserByUid(Integer user_id) {
		userDAO.deleteUserByUid(user_id);
		
	}

}
