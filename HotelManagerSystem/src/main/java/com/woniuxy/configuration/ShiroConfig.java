package com.woniuxy.configuration;
import java.util.LinkedHashMap;
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
		userRealm.setCredentialsMatcher(matcher);   //加入MD5验证后打开该注释
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
		bean.setLoginUrl("/html/login.html");
		//配置无权限页面
		bean.setUnauthorizedUrl("error.html");
	

		//设置过滤器链
		Map<String, String>map = new LinkedHashMap<>();
		//放行首页静态文件
		map.put("/css/**","anon");
		map.put("/face-user/**","anon");
		map.put("/housedetailinfo/**","anon");
		map.put("/img/**", "anon");
		map.put("/js/**", "anon");
		map.put("/images/**", "anon");
		map.put("/scripts/**", "anon");
		map.put("/style/**", "anon");
		map.put("/fonts/**", "anon");
		
	
		//登录请求
		map.put("/user/login", "anon");
		//注册页面
		map.put("/html/sign-up.html", "anon");
		//酒店介绍
//		map.put("/introuduce.html", "anon");
		map.put("/comment/**", "anon");
		//logout
		map.put("/logout", "logout");
//		map.put("/vipinfo.html","anon"); 
//		map.put("/user/getvipbyadmin", "anon");      //用于vip的测试
//		map.put("/user/register", "anon");
		//map.put("/druid/**", "anon");
		map.put("/", "anon");
		map.put("/login.html", "anon");
		map.put("/payOrder/savePayResult","anon");
		
		//map.put("/user/delete","authc,perms[user:delete]"); //管理员删除账号
		// /**
		map.put("/**", "authc");
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}