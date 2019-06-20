package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;

public interface UserDAO {
	@Select(" select * from t_user")
	public List<User> allUser();
	@Insert("insert into t_user(user_acc,user_pwd) values(#{user_acc},#{user_pwd})")
	public void addUser(User user);
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserByAcc(String user_acc);
	@Select("select *from t_user_info where user_id=#{user_id}")
	public UserInfo getInfoByUid(Integer user_id);
	@Update("update t_user_info set user_info_name=#{user_info_name},user_info_tel=#{user_info_tel},user_info_idcard=#{user_info_idcard} where user_id=#{user_id}")
	public void updateInfo(UserInfo userInfo);
}
