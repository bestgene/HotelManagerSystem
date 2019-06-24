package com.woniuxy.realm;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.woniuxy.pojo.Perm;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;


public class UserRealm extends AuthorizingRealm{
	
	private User User;
	
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("正在授权...");
		String user_acc = (String)principals.getPrimaryPrincipal();
		User user=userService.findUserByAcc(user_acc);//查询当前用户的user表信息
		Integer role_id = user.getRole_id();//获取当前user的角色id
		Role role=userService.selectRoleAndPerms(role_id);//查询当前角色的role和perm信息
		Set<String> roles=new HashSet<>();  //创建角色set
		roles.add(role.getRole_name());  //将当前用户角色放入roleSet
		
		List<Perm> userPerms=role.getPerms(); //获取当前角色权限list
		Set<String> perms = new HashSet<>(); 
		for(Perm perm:userPerms){  
			perms.add(perm.getPerm_name()); //将当前用户权限放入permSet
		}
		
		System.out.println(perms);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.addStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		System.out.println("正在认证");
		//获取账号

		String user_acc = (String)token.getPrincipal();
		User user = userService.findUserByAcc(user_acc);


		System.out.println(user);
		if (user == null){
			return null;
		}
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(user.getUser_acc().toString(),user.getUser_pwd(),getName());
		return info;
	}

}
