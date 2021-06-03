package com.login.login.config.oauth;

import com.login.login.config.auth.principaldetail;
import com.login.login.user.memberdao;
import com.login.login.user.membervo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class principaloauth2userservice extends DefaultOAuth2UserService{
    @Autowired
    private memberdao memberdao;
    
   @Override//구글로 부터 받은 userrequest를 여기서 후처리를 해준다
   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       System.out.println("userrequest"+userRequest);///어떤 oauth로 로그인했는지 확인가능 ex google이면 google이라고 나옴
       System.out.println("userrequestaccesstoken"+userRequest.getAccessToken().getTokenValue());
       //구글로그인 버튼 클릭->구글로그인창->로그인완료->code(ouath-client라이브러리)가받음->accesstoken요청
       //accesstoken받음=userequset정보임->회원프로필 받아야함->loadUser호출->회원프로필받아줌

       OAuth2User oAuth2User=super.loadUser(userRequest);///얘가 우리프로필을 다들고있는거다

        String provider=userRequest.getClientRegistration().getClientId();//얘가구글이다
        String providerid=oAuth2User.getAttribute("sub");
        String email=oAuth2User.getAttribute("email");
        String name=oAuth2User.getAttribute("name");///정보는 맵에서 꺼낸다 
        String pwd="1111";
        String role="ROLE_USER";
       membervo membervo=memberdao.findByEmail(email);
        if(membervo==null){
            membervo membervo2=new membervo();
            membervo2.setEmail(email);
            membervo2.setName(name);
            membervo2.setProvider(provider);
            membervo2.setProviderid(providerid);
            membervo2.setPwd(pwd);
            membervo2.setRole(role);
            memberdao.save(membervo2);
        }   
       //회원가입 강제 진행
       return new principaldetail(membervo, oAuth2User.getAttributes());///principal이니 가능하다 이걸하면 authen거기로
   }
}
