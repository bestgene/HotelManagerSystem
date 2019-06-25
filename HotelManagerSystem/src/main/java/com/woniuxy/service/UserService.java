package com.woniuxy.service;
import java.util.List;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.Telpojo;
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
	
	public User findUserByUid(User user);
	
	public void register(User user);

	public Telpojo findbyuseracc(Telpojo telpojo);
	
	public void telregister(Telpojo telpojo);
	
	public User findUserIdByacc(User user);
	
	public Vip findLevelIdByuserId(User user);
	
	public Level findQuatoBylevelId(Vip vip);

}
