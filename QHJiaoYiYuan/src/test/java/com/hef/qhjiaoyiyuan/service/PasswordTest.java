package com.hef.qhjiaoyiyuan.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Date 2020/3/8
 * @Author lifei
 */
public class PasswordTest {

    @Test
    public void BCryptPasswordTest(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode("world@pass");
        // $2a$10$6rSFrCQ0Q1iDwnXnahwAVugDUlI5LSSPcqmtdn.7dGhkYJXi5bbV.
        System.out.println(encodePassword);

    }
}
