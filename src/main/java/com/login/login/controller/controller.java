package com.login.login.controller;

import com.login.login.config.auth.principaldetail;
import com.login.login.user.memberservice;
import com.login.login.user.membervo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;//이걸로 임포트해야함 20210603
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controller {

    private boolean yorn=false;
    private final String user="ROLE_USER";


    @Autowired
    private memberservice memberservice;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @GetMapping("/test/login")
    public @ResponseBody String logintest(Authentication authentication,@AuthenticationPrincipal UserDetails userDetails) {
            System.out.println("testlogin"+authentication.getPrincipal());//import org.springframework.security.core.Authentication; 이걸로 임포트해야함 20210603
            principaldetail principaldetail=(principaldetail)authentication.getPrincipal();
            
            System.out.println("authentication"+principaldetail.getMembervo());
            System.out.println("userdetails"+userDetails.getUsername());
            return "test";
    }
    @GetMapping("/test/oauth/login")
    public @ResponseBody String logintestoauth(Authentication authentication,@AuthenticationPrincipal OAuth2User oAuth2User) {
            System.out.println("testloginoauth"+authentication.getPrincipal());
            OAuth2User OAuth2User=(OAuth2User)authentication.getPrincipal();///oauth는 로그인시 OAuth2User로 받아야함
            ///두가지 방법으로 접근이 가능하다 Authentication/@AuthenticationPrincipal
            System.out.println("authentication"+OAuth2User.getAttributes());
            System.out.println("oauth"+oAuth2User.getAttributes());
            return "oauth";
    }
    @GetMapping("/user")
    public @ResponseBody String user(){
            return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody  String admin(){
            return "admin";
    }
    @GetMapping("/manage")
    public @ResponseBody  String manage(){
            return "manage";
    }
    @GetMapping("/loginpage")
    public  String loginpage(){
            return "loginpage";
    }
    @GetMapping("/joinpage")
    public  String joinpage(){
            return "joinpage";
    }
    @PostMapping("/joinpro")
    public String joinpro(membervo membervo) {

        String hashpwd=bCryptPasswordEncoder.encode(membervo.getPwd());
        membervo.setRole(user);
        membervo.setPwd(hashpwd);
        yorn=memberservice.isnertuser(membervo);
        if(yorn){
                return "loginpage";
        }else{
                return "joinpage";
        }  
    }

    @Secured("ROLE_ADMIN")//우와 시큐리티 @EnableGlobalMethodSecurity(securedEnabled =  true) 로 하는건데 권한에 따라 페이지를 조절함 완전편하네 20210603 얘는한개
    @GetMapping("/infor")
    public @ResponseBody String infor() {

        return "infor";
            
    }
    @PreAuthorize("hasRole('ROLE_MANAGE') or hasRole('ROLE_ADMIN') ")//실행직전 실행되는건데 @Secured는 한개 @PreAuthorize는 여러개
    @GetMapping("/data")
    public @ResponseBody String data() {

        return "data";
            
    }

}
 