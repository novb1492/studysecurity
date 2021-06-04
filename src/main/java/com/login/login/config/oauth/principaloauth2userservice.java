package com.login.login.config.oauth;

import com.login.login.config.auth.principaldetail;
import com.login.login.config.oauth.provider.facebookuserinfor;
import com.login.login.config.oauth.provider.googleuserinfor;
import com.login.login.config.oauth.provider.oauth2userinfor;
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
       System.out.println("userrequest"+userRequest.getClientRegistration());///어떤 oauth로 로그인했는지 확인가능 ex google이면 google이라고 나옴
       System.out.println("userrequestaccesstoken"+userRequest.getAccessToken().getTokenValue());
       //구글로그인 버튼 클릭->구글로그인창->로그인완료->code(ouath-client라이브러리)가받음->accesstoken요청
       //accesstoken받음=userequset정보임->회원프로필 받아야함->loadUser호출->회원프로필받아줌

       OAuth2User oAuth2User=super.loadUser(userRequest);///얘가 우리프로필을 다들고있는거다
        System.out.println("getattribute"+oAuth2User.getAttributes());

        oauth2userinfor oauth2userinfor=null;

        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글로그인요청");
            oauth2userinfor=new googleuserinfor(oAuth2User.getAttributes());

        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북로그인요청");
            oauth2userinfor=new facebookuserinfor(oAuth2User.getAttributes());

        }else{
            System.out.println("우리는 구글 페이스북만 지원합니다");
        }

        String provider=oauth2userinfor.getProvider();//얘가구글이다/페이스북이다
        String providerid=oauth2userinfor.getProviderid();
        String email=oauth2userinfor.getEmail();
        String name=oauth2userinfor.getName();///정보는 맵에서 꺼낸다 
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
