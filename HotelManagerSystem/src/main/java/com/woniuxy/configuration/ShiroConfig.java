package com.woniuxy.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woniuxy.realm.UserRealm;

@Configuration
public class ShiroConfig {
	
	@Bean
	public CredentialsMatcher matcher(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		return matcher;
	}
	@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		System.out.println("创建realm");
		UserRealm userRealm = new UserRealm();
		System.out.println(userRealm);
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}
	@Bean
	public SecurityManager securityManager(UserRealm userRealm){
		System.out.println("创建securityManager");
		System.out.println(userRealm);
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm);
		return manager;
	}
	
	//shiro过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		System.out.println("正在创建过滤器");
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//配置安全管理器
		bean.setSecurityManager(securityManager);
		//配置登录页面
		bean.setLoginUrl("login.html");
		//配置无权限页面
		bean.setUnauthorizedUrl("login.html");
		//设置过滤器链
		Map<String, String>map = new HashMap<>();
		
		//anno 任何人都能登录
//		map.put("/index", "anon");
		map.put("/html/login.html", "anon");
		map.put("/User/login", "anon");
		map.put("/check/telcheck", "anon");
		map.put("/druid/**", "anon");
		map.put("sign-up.html", "anon");//无权限可以访问的注册界面
		//logout
		map.put("/logout", "logout");
		
		// /**
		//map.put("/**", "authc");
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}
