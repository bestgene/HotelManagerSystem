package com.woniuxy.service;

import java.math.BigDecimal;
import java.util.List;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.pojo.Vip;

public interface UserService {
	public List<User> allUser();
	public void addUser(User user);
	public void deleteUserByUid(Integer user_id);
	public User findUserByAcc(String user_acc);
	public UserInfo getInfoByUid(Integer user_id);
	public void updateInfo(UserInfo userInfo);
	public Role selectRoleAndPerms(Integer role_id);
	public Vip getVipByUid(Integer user_id);
	public Level getLevelByVipId(Integer level_id);
	public UserInfo selectUserInfoByTelOrIdcard(String user_info_tel,String user_info_idcard);
	public BigDecimal getDiscountByTelOrIdcard(String user_info_tel, String user_info_idcard);   //根据身份证或者电话获取会员等级
	public Role selectRoleByUserId(Integer user_id);   //根据当前用户user
}

