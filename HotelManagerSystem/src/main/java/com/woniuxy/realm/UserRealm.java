package com.woniuxy.realm;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("正在授权");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("正在认证");
		//获取账号
		Integer uid = Integer.parseInt((String)token.getPrincipal());
		User user = userService.findUserByUid(uid);
		System.out.println(user);
		if (user == null){
			return null;
		}
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(user.getUser_acc().toString(),user.getUser_pwd(),getName());
		return info;
	}

}
