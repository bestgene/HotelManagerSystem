package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.woniuxy.pojo.Perm;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;

public interface UserDAO {
	@Select(" select * from t_user")
	public List<User> allUser();
	@Delete("delete from t_user where user_id=#{user_id}")
	public void deleteUserByUid(Integer user_id);
	@Insert("insert into t_user(user_acc,user_pwd) values(#{user_acc},#{user_pwd})")
	public void addUser(User user);
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserByAcc(String user_acc);
	@Select("select *from t_user_info where user_id=#{user_id}")
	public UserInfo getInfoByUid(Integer user_id);
	@Update("update t_user_info set user_info_name=#{user_info_name},user_info_tel=#{user_info_tel},user_info_idcard=#{user_info_idcard} where user_id=#{user_id}")
	public void updateInfo(UserInfo userInfo);
	
	@Select("select *from t_perm where role_id=#{role_id}")
	@Results({
		@Result(id=true,column="perm_id",property="perm_id"),
		@Result(column="perm_name",property="perm_name")
	})
	public List<Perm> selectPermsByRid(Integer role_id);
	@Select("select *from t_role where role_id=#{role_id}")
	@Results({
		@Result(id=true,column="role_id",property="role_id"),
		@Result(column="role_name",property="role_name"),
		@Result(column="role_id",property="perms",
		many=@Many(
				select="com.woniuxy.dao.UserDAO.selectPermsByRid",
				fetchType=FetchType.EAGER
				)
				)
	})
	public Role selectRoleAndPerms(Integer role_id);
}
