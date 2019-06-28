package com.woniuxy.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.Telpojo;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.pojo.Vip;

public interface UserService {
	public List<User> allUser();   //获得所有用户信息
	public void addUser(User user);   //用户注册
	public void deleteUserByUid(Integer user_id);  //管理员删除指定用户
	public User findUserByAcc(String user_acc);    //根据账号查找用户
	public UserInfo getInfoByUid(Integer user_id); //根据userID获得用户信息表
	public void updateInfo(UserInfo userInfo);     //用户更改个人信息
	public Role selectRoleAndPerms(Integer role_id); //权限一对多查询
	public Vip getVipByUid(Integer user_id);         //根据user_id查询Vip表：可用于用户自主查询自己的vip信息
	public Level getLevelByVipId(Integer level_id);  //通过会员等级ID查询等级信息表
	public UserInfo selectUserInfoByTelOrIdcard(String arg0, String arg1, String arg2);  //根据电话或者身份证查询获取用户信息
	public Map<String, Object> getDiscountByTelOrIdcard(String user_info_name, String user_info_tel, String user_info_idcard);   //根据身份证或者电话获取会员等级
	public Role selectRoleByUserId(Integer user_id);   //根据当前用户user
	public void addDefualtInfo(String arg0, String arg1,Integer arg2,String arg3);
	public void addDefualtUser(String arg0,String arg1);  //创建默认用户，以他提供的手机号为acc，123456位默认密码
	public void addDefualtVip(String arg0,Integer arg1);//创建默认用户的vip信息表
	public User findUserByuserAcc(Telpojo telpojo);//通过短信验证中user_acc去查找对应的账户
	public void Telregister(Telpojo telpojo);//通过短信验证进行注册用户

	public Map<String, Object> getVipByUserid(Integer user_id);//用户根据自己的uid查出自己的info_id和discount

}

