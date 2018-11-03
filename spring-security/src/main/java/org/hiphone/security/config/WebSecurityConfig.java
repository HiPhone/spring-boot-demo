package org.hiphone.security.config;

import org.hiphone.security.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author HiPhone
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserDetailService();
    }

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
                .antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/user/**").access("hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')" )
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.usernameParameter("username") //自定义用户的name的key
                //.passwordParameter("password") //自定义用户的password的key
                //.failureUrl("/login?error")  //登陆失败重定向的url
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
                .rememberMe()
                .tokenValiditySeconds(6000000) //记住我的时间
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
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(new PasswordEncode())
//                .withUser("user")
//                .password("password")
//                .roles("USER");
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncode());
    }
}
