package com.woniuxy.dao;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.Vip;
import com.woniuxy.pojo.telpojo;


public interface UserDAO {
	@Select(" select * from user")
	public List<User> allUser();
	
	@Insert("insert into user values(default,#{uname},#{pwd})")
	public void addUser(User user);
	
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserByUid(User user);
	
	@Insert("insert into t_user(user_acc,user_pwd)values(#{user_acc},#{user_pwd})")
	public void register(User user);
	
	@Select("select * from t_user where user_acc=#{user_acc}")
	public telpojo findbyuseracc(telpojo telpojo);
	
	@Insert("insert into t_user(user_acc,user_pwd)values(#{user_acc},#{user_pwd})")
	public void telregister(telpojo telpojo);
	
	//通过user_acc去查找user_id
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserIdByacc(User user);
	
	//通过user_id查询用户的会员等级
	@Select("select * from t_vip where user_id=#{user_id}")
	public Vip findLevelIdByuserId(User user);
	
	//通过会员等级查询会员的消费额度！
	@Select("select * from t_level where level_id=#{level_id}")
	public Level findQuatoBylevelId(Vip vip);
	
}
