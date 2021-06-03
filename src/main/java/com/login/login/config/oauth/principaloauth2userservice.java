package com.login.login.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class principaloauth2userservice extends DefaultOAuth2UserService{
    
   @Override//구글로 부터 받은 userrequest를 여기서 후처리를 해준다
   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       System.out.println("userrequest"+userRequest);///어떤 oauth로 로그인했는지 확인가능 ex google이면 google이라고 나옴
       System.out.println("userrequestaccesstoken"+userRequest.getAccessToken().getTokenValue());
       //구글로그인 버튼 클릭->구글로그인창->로그인완료->code(ouath-client라이브러리)가받음->accesstoken요청
       //accesstoken받음=userequset정보임->회원프로필 받아야함->loadUser호출->회원프로필받아줌
       System.out.println("supteruserrequest"+super.loadUser(userRequest).getAttributes());

       OAuth2User oAuth2User=super.loadUser(userRequest);
       
       return super.loadUser(userRequest);///얘가 우리프로필을 다들고있는거다
   }
}
