# studysecurity

예상대로 시큐리티는 시큐리티 전용세션이있었고
시큐리티세션엔 authentication객체만 들어갈 수있다
authentication는
userdetails(일반적로그인)
oath2user(oauth로그인)
이렇게 두개만 들어갈 수 있다
그리고 그둘은 
principaldetails타입으로 묶여서
다룰수있다

public class principaldetail implements UserDetails,OAuth2User{

