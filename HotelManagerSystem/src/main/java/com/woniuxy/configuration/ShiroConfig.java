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
	
	
	//shiro过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		System.out.println("正在创建过滤器");
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		//配置安全管理器
		bean.setSecurityManager(securityManager);
		//配置登录页面
//		bean.setLoginUrl("login.html");
		//配置无权限页面
		bean.setUnauthorizedUrl("error.html");
		//设置过滤器链
		Map<String, String>map = new HashMap<>();

		//anno 任何人都能登录
		//			map.put("/index", "anon");
		map.put("/login.html", "anon");
		map.put("/user/login", "anon");
		/*map.put("/vipinfo.html","anon"); 
		map.put("/user/getvipbyadmin", "anon");      用于vip的测试*/
		map.put("/error.html", "anon");
		map.put("/user/register", "anon");
		map.put("/check/emailcheck", "anon");//邮件验证
		map.put("/druid/**", "anon");
		map.put("/sign.html", "anon"); 
		map.put("/face-user/index.html", "anon");
		
		map.put("/user/delete","authc,perms[user:delete]"); //管理员删除账号
		//logout
		map.put("/logout", "logout");
		// /**
		map.put("/**", "authc");
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
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
}