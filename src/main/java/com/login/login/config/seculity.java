package com.login.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//사용하겠다 선언
public class seculity extends WebSecurityConfigurerAdapter {



    @Bean
    public BCryptPasswordEncoder encodepwd() {
        return new BCryptPasswordEncoder();
        
    }
  
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN' or hasRole('ROLE_MANAGER'))")
            .antMatchers("/manager/**").access("hasRole(hasRole('ROLE_MANAGER'))")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginpage")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/");
    }

    
}
