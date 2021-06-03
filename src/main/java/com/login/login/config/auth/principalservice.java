package com.login.login.config.auth;





import com.login.login.user.memberdao;
import com.login.login.user.membervo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class principalservice implements UserDetailsService{

    @Autowired
    private memberdao memberdao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
        membervo membervo=memberdao.findvobyemail(username);
        return new principaldetail(membervo);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
    
}
