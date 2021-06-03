package com.login.login.config.auth;





import com.login.login.user.memberdao;
import com.login.login.user.membervo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서.loginProcessingUrl("/login")
//오면 자동으로  loadUserByUsername가 실행됨
@Service
public class principalservice implements UserDetailsService{

    @Autowired
    private memberdao memberdao;

    //시큐리티 세션(내부authentication(내부userdetails))이렇게 감싸진다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
        membervo membervo=memberdao.findByEmail(username);
        return new principaldetail(membervo);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
}
