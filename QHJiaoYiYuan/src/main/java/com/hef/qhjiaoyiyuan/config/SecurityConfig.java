package com.hef.qhjiaoyiyuan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @Date 2020/3/5
 * @Author lifei
 */
@Configuration
@EnableWebSecurity // 启用web安全性
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;

    // remember me 的时效
    private static final int TOKEN_VALIDITY_SECONDS = 7 * 24 * 60 * 60;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    /**
     * 配置对请求的权限，配置这个之后
     * 在重写{@code configure(HttpSecurity http)} 方法之前，都能使用一个简单却功能完备的登陆页。
     * 一旦重写了这个方法，就失去了这个简单的登陆页。
     * 通过调用 formLogin()  方法，能把登陆页找回来
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 要求所有进入应用的http请求都要进行认证
                // 如果这里使用 anyRequest()  将导致 {@code localhost将您重定向的次数过多} 的异常
//                .antMatchers("/","/home").authenticated()
                .antMatchers("/managerController/**").authenticated()
                .antMatchers("/articleController/addArticleSubmit","/articleController/showSuccessView/**").authenticated()
                .anyRequest().permitAll()
                .and()
                // 配置支持基于表单的登陆以及HTTPBasic方式的认证
                // 调用该方法能将默认的登陆页找回来
                .formLogin()
                // 启用自定义的登陆页
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .and().httpBasic()
                .and().rememberMe().tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                .and().logout().logoutSuccessUrl("/managerController/manageIndex");
    }

    /**
     * 配置基于关系型数据库的权限认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
