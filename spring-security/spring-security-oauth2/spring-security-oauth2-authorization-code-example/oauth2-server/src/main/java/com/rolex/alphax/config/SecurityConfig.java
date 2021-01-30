/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

import com.rolex.alphax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author rolex
 * @since 2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  允许所有用户访问"/"和"/index.html"
        /*http.csrf()
                .and()
                .authorizeRequests().antMatchers("/", "/index.html", "/403.html", "/static/css/**").permitAll()
                .anyRequest().access("@permissionServiceImpl.hasPermission(request, authentication)")   // 其他地址的访问均需验证权限
                .and()
                .formLogin()
                .loginPage("/login.html")   //  登录页
                .failureUrl("/login-error.html").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html")
                .and()
                .logout()
                .logoutSuccessUrl("/index.html");*/
        http.authorizeRequests()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                .httpBasic() //Basic登录
                .and()
                .csrf().disable(); //关跨域保护
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
