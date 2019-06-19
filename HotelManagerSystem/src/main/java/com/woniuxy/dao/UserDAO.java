package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import com.woniuxy.pojo.User;

public interface UserDAO {
	@Select(" select * from user")
	public List<User> allUser();
	@Insert("insert into t_user(user_acc,user_pwd) values(#{user_acc},#{user_pwd})")
	public void addUser(User user);
	@Select("select * from user where uid=#{uid}")
	public User findUserByUid(Integer uid);
}
