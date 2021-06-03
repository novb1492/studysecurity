package com.login.login.controller;

import com.login.login.user.memberservice;
import com.login.login.user.membervo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class controller {

    private boolean yorn=false;
    private final String user="user";


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

}
 