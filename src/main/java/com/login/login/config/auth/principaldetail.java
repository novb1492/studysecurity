package com.login.login.config.auth;

import java.util.Collection;

import com.login.login.user.membervo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


//시큐리티가 로그인을 낚아채서 로그인인시키고
//로그인을 진행이 완료돠면 예상대로 시큐리티세션이 넣는다(security contextholder)
//오브젝트는 authentication으로 만 고정
//
public class principaldetail implements UserDetails{

    private membervo membervo;

    public principaldetail(membervo membervo)
    {
        this.membervo=membervo;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return membervo.getPwd();
    }

    @Override
    public String getUsername() {
        return membervo.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
