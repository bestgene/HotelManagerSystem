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
import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.Perm;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.Telpojo;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.pojo.Vip;

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
	@Select("select *from t_vip where user_id=#{user_id}") //test
	public Vip getVipByUid(Integer user_id);
	@Select("select *from t_level where level_id=#{level_id}")//test
	public Level getLevelByVipId(Integer level_id);
	@Select("select *from t_user_info where user_info_tel=#{arg0} or user_info_idcard=#{arg1}")
	public UserInfo selectUserInfoByTelOrIdcard(String arg0,String arg1);
	@Select("select *from t_user u,t_role r where u.role_id=r.role_id and u.user_id=#{user_id}")
	public Role selectRoleByUserId(Integer user_id);
	@Insert("insert into t_user(user_acc,user_pwd) values(#{arg0},#{arg1})")
	public void addDefualtUser(String arg0,String arg1);
	@Insert("insert into t_user_info(user_info_name,user_info_tel,user_info_idcard,user_id) values('默认用户',#{arg0},#{arg1},#{arg2})")
	public void addDefualtInfo(String arg0,String arg1,Integer arg2); 
	@Insert("insert into t_vip (vip_number,user_id,level_id) values(#{arg0},#{arg1},1)")
	public void addDefualtVip(String arg0,Integer arg1);
	
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
	
	@Select("select * from t_user where user_id=#{user_id}")
	public User selectUserByUid(Integer user_id);
	
	//短信验证时候的通过user_acc查找账户
	@Select("select * from t_user where user_acc=#{user_acc}")
	public User findUserByuserAcc(Telpojo telpojo);
	//短信验证时候进行的注册 的功能
	@Insert("insert into t_user(user_acc,user_pwd) values(#{user_acc},#{user_pwd})")
	public void Telregister(Telpojo telpojo);
}