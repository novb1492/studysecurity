package com.login.login.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import com.login.login.user.membervo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


//시큐리티가 로그인을 낚아채서 로그인인시키고
//로그인을 진행이 완료돠면 예상대로 시큐리티세션이 넣는다(security contextholder)
//오브젝트는 authentication으로 만 고정
//uservo=>userdetails로 객체가 바뀜
//security session=>authentication=>userdetails순서대로 꺼내진다

public class principaldetail implements UserDetails{

    private membervo membervo;

    public principaldetail(membervo membervo)
    {
        this.membervo=membervo;
    }

    //해당 유저의 권한을 리턴한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority>collet=new ArrayList<>();
        collet.add(new GrantedAuthority(){

            @Override
            public String getAuthority() {
                return membervo.getRole();
            }
            
        });
        return collet;
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

        //우리사이트에서 휴면계정으로 바뀐다
        // logindate를 만들어서
        //현재날-logindate 1년이상이면
        //false리턴하면됨
        return true;
    }
    
}
