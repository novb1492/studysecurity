package com.login.login.config;

import com.login.login.config.oauth.principaloauth2userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

  //구글로그인이 완료된 후처리를 해줘야함
            //1.코드받기(인증)2.엑세스토큰받기(권한생성)
            //3.사용자 프로필정보 가져오기
            //4.그정보를토대로 회원가입 혹은 정보가 좀 모자라면 ex주소라던가
            //모자라면 추가적인 회원가입을 시켜야함
            //구글로그인이 완료되면 (엑세스토큰+사용자프로필정보) 다받음
        

@Configuration
@EnableWebSecurity//사용하겠다 선언
@EnableGlobalMethodSecurity(securedEnabled =  true,prePostEnabled =  true)//securedEnabled = secured 어노테이션 활성화,prePostEnabled =preauthorize/postauthosize활성화
public class seculity extends WebSecurityConfigurerAdapter {

    @Autowired
    private principaloauth2userservice principaloauth2userservice;


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
            .loginPage("/loginpage")
            .userInfoEndpoint()///구글/페이스북등에게 신청한후받아서
            .userService(principaloauth2userservice);////얘랑 처리함  


    }
}
