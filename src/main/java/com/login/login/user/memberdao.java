package com.login.login.user;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface memberdao extends JpaRepository<membervo,Integer> {
    
    @Query(value = "select email from members where email=?1",nativeQuery = true)
    public String findbyemail(String email);

    //@Query(value = "select * from members where email=?1",nativeQuery = true)
    //public membervo findvobyemail(String email);

    //우와 sql문이 이렇게된다 쿼리메소드라고한다 20210603
    public membervo findByEmail(String username);
}
