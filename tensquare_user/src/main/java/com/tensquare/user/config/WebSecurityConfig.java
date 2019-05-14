package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hftang
 * @date 2019-05-13 10:18
 * @desc 安全配置类
 * <p>
 * 因为 springsecurity 的拦截链拦截了所有的请求
 * 为了让能访问的到 所以配置下
 *
 * WebSecurityConfigurerAdapter 这个类是让自动可以被加载
 *让系统知道自己是一个配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


}
