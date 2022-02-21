package com.xjh.web.config;

import com.xjh.web.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author xjh
 * @date 2022/2/13 17:08
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userDetailsService")
    private MyUserDetailsService userDetailsService;

    @Resource
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.csrf().disable();

        http.authorizeRequests()
                        .antMatchers("/ajax/**","/css/**","/file/**").permitAll()
                        .antMatchers("/fonts/**","/html/**","/i18n/**").permitAll()
                        .antMatchers("/img/**","/js/**","/ruoyi/**").permitAll()
                        .antMatchers("/favicon.ico","/ruoyi.png").permitAll()
                        .antMatchers("/main").permitAll();


        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
//                .antMatchers("/index").hasRole("admin")
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/403");

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(14 * 24 * 60 * 60)
                .rememberMeCookieName("remember-me")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        return jdbcTokenRepository;
    }
}
