package com.login.login.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="memberstest")
@NoArgsConstructor
@Entity
public class membervo {
    
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email",length = 30,unique=true,nullable = false)
    private String email;

    @Column(name="pwd",length = 100,nullable = false )
    private String pwd;

    @Column(name="name",length = 20,nullable = false)
    private String name;

    @Column(name="created")
    @CreationTimestamp  
    private Timestamp created;

    @Column(name="role",nullable=false)
    private String role;

    @Column(name="provider")
    private String provider;
    
    @Column(name="providerid")
    private String providerid;
}