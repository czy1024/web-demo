package com.luna.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Luna@win10
 * @date 2020/4/9 13:29
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	/**
	 * 静态资源设置
	 */
	@Override
	public void configure(WebSecurity webSecurity) {
		//不拦截静态资源,所有用户均可访问的资源
		log.info("不拦截静态资源,所有用户均可访问的资源");
		webSecurity.ignoring().antMatchers(
				"/ajax/**",
				"/css/**",
				"/js/**",
				"/images/**"
//				"/crud/**",
//				"/page/**",
//				"/dept/**",
//				"/pageUser/**"
		);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//super.configure(http);
		log.info("定制请求的授权规则");
		http.authorizeRequests()
				.antMatchers("/security/level1/**").hasRole("VIP1")
				.antMatchers("/security/level2/**").hasRole("VIP2")
				.antMatchers("/security/level3/**").hasRole("VIP3");

		//开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
		http.formLogin()
				.usernameParameter("user").passwordParameter("pwd")
				.loginPage("/security/userlogin");
		//1、/login来到登陆页
		//2、重定向到/login?error表示登陆失败
		//3、更多详细规定
		//4、默认post形式的 /login代表处理登陆
		//5、一但定制loginPage；那么 loginPage的post请求就是登陆


		//开启自动配置的注销功能。
		http.logout().logoutSuccessUrl("/security/welcome");
		//注销成功以后来到首页
		//1、访问 /logout 表示用户注销，清空session
		//2、注销成功会返回 /login?logout 页面；

		//开启记住我功能
		http.rememberMe().rememberMeParameter("remember");
		//登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
		//点击注销会删除cookie

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//定义认证规则
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder())
				.withUser("zhangsan").password(passwordEncoder().encode("123456")).roles("VIP1", "VIP2")
				.and()
				.withUser("lisi").password(passwordEncoder().encode("123456")).roles("VIP2", "VIP3")
				.and()
				.withUser("wangwu").password(passwordEncoder().encode("123456")).roles("VIP1", "VIP3");

	}
}
