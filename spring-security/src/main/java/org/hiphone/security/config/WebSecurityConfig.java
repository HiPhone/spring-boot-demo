package org.hiphone.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author HiPhone
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置上下文,/echo-test不需要验证身份即可调用,身份验证失败跳转到/login接口,/login默认为登陆界面
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/echo-test").permitAll()
                //指定能访问接口的单个角色
                .antMatchers("/admin/**").hasRole("ADMIN")
                //指定能访问接口的多个角色
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                //登出配置,使用/login?logout登出
                .logout()
                //.logoutUrl("/logout")  //配置登出界面
                //.logoutSuccessUrl("/login/success")  //配置登出成功界面
                //.invalidateHttpSession(true)  //是否将session无效化
                //.logoutSuccessHandler() //登出成功的操作
                //.addLogoutHandler() //登出时的操作
                .permitAll()
                //禁用csrf()防跨域,方便前后端分离调试
                .and()
                .csrf().disable();
    }

    /**
     * TODO 写死的用户和密码,后续修改为其他验证方式
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new PasswordEncode())
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
