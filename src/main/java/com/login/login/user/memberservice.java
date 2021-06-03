package com.login.login.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class memberservice {
    private final boolean yes=true;
    private final boolean no=false;
    @Autowired
    private memberdao memberdao;

    public boolean isnertuser(membervo membervo) {

        try {
            memberdao.save(membervo);
            return yes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }
}
