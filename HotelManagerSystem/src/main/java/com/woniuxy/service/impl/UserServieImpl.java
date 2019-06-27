package com.woniuxy.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
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
	public Map<String, Object> getDiscountByTelOrIdcard(String user_info_tel, String user_info_idcard){ 
		String defaultAcc="";//用户不存在时，用于存储默认账号
		System.out.println("当前用户："+user_info_tel+","+user_info_idcard);
		if("".equals(user_info_tel)){
			user_info_tel=UUID.randomUUID().toString().substring(0, 10);
			defaultAcc=user_info_idcard; //提供的是身份证号，就用身份证号为默认账号
		}
		if("".equals(user_info_idcard)){
			user_info_idcard=UUID.randomUUID().toString().substring(0, 17);
			defaultAcc=user_info_tel;    //提供的是手机号，就用手机为默认账号
		}
		if(user_info_idcard!=null&&user_info_tel!=null){
			defaultAcc=user_info_tel.substring(0,3)+user_info_idcard.substring(0,3);
		}  //手机和身份证都提供时
		UserInfo info=selectUserInfoByTelOrIdcard(user_info_tel, user_info_idcard);     
		if(info==null){
			System.out.println("用户信息不存在");
			System.out.println("默认账号："+defaultAcc);
			String defualtPwd=new SimpleHash("MD5","123456", null,1024).toString();
			addDefualtUser(defaultAcc,defualtPwd); //info表不存在时，创建默认用户，默认密码123456
			System.out.println("默认用户创建成功");
			Integer user_id=findUserByAcc(defaultAcc).getUser_id();
			System.out.println(user_info_tel+","+user_info_idcard+","+user_id);
			addDefualtInfo(user_info_tel,user_info_idcard,user_id);
			System.out.println("添加默认信息成功");
			String vip_number=UUID.randomUUID().toString();//为vipnumber产生随机编号
			vip_number=vip_number.substring(0,7);
			addDefualtVip(vip_number,user_id);
			System.out.println("添加默认vip信息成功");
			info=getInfoByUid(user_id);
		}
		Vip vip=getVipByUid(info.getUser_id());
		Level level=getLevelByVipId(vip.getLevel_id());
		BigDecimal discount = level.getLevel_discount();
		Map<String,Object> map=new HashMap<>();
		map.put("info_id",info.getUser_info_id());  //info_id和discount放入了map，该函数返回map
		map.put("discount",discount);
		return map;
	}

	@Override
	public Role selectRoleByUserId(Integer user_id) {
		return userDAO.selectRoleByUserId(user_id);
	}

	@Override
	public void addDefualtUser(String arg0,String arg1) {
		userDAO.addDefualtUser(arg0,arg1);
	}

	@Override
	public void addDefualtInfo(String arg0, String arg1, Integer arg2) {
		userDAO.addDefualtInfo(arg0, arg1, arg2);
	}

	@Override
	public void addDefualtVip(String arg0,Integer arg1) {
		userDAO.addDefualtVip(arg0,arg1);
		
	}
}
