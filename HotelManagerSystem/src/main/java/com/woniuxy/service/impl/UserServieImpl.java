package com.woniuxy.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.UserDAO;
import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.pojo.Vip;
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

	@Override
	public Vip getVipByUid(Integer user_id) {
		return userDAO.getVipByUid(user_id);
	}

	@Override
	public Level getLevelByVipId(Integer level_id) {
		return userDAO.getLevelByVipId(level_id);
	}

	@Override
	public UserInfo selectUserInfoByTelOrIdcard(String user_info_tel, String user_info_idcard) {
		return userDAO.selectUserInfoByTelOrIdcard(user_info_tel, user_info_idcard);
	}
	
	@Override
	public BigDecimal getDiscountByTelOrIdcard(String user_info_tel, String user_info_idcard){   
		UserInfo info=selectUserInfoByTelOrIdcard(user_info_tel, user_info_idcard);
		Vip vip=getVipByUid(info.getUser_id());
		Level level=getLevelByVipId(vip.getLevel_id());
		BigDecimal discount = level.getLevel_discount();
		return discount;
	}

	@Override
	public Role selectRoleByUserId(Integer user_id) {
		return userDAO.selectRoleByUserId(user_id);
	}
}
