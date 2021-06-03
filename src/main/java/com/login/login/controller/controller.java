package com.login.login.controller;

import com.login.login.user.memberservice;
import com.login.login.user.membervo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 