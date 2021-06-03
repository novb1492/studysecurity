package com.login.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//사용하겠다 선언
@EnableGlobalMethodSecurity(securedEnabled =  true,prePostEnabled =  true)//securedEnabled = secured 어노테이션 활성화,prePostEnabled =preauthorize/postauthosize활성화
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
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginpage")
            //.usernameParameter("email") 이렇게하면 UserDetails loadUserByUsername(String username) username->email로 가능
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()////시큐리티에서 .oauth2Login()해줘야함 20210603
            .loginPage("/loginpage");//구글로그인이 완료된 후처리를 해줘야함
    }

    
}
