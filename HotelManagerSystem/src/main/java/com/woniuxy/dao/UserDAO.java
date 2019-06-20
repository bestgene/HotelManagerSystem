package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.User;


public interface UserDAO {
	@Select(" select * from user")
	public List<User> allUser();
	@Insert("insert into user values(default,#{uname},#{pwd})")
	public void addUser(User user);
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserByUid(User user);
}
