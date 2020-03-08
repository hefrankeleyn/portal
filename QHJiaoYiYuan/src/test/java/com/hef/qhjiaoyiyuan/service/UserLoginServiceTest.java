package com.hef.qhjiaoyiyuan.service;

import com.hef.qhjiaoyiyuan.bean.QHUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date 2020/3/8
 * @Author lifei
 */
@SpringBootTest
public class UserLoginServiceTest {

    @Autowired
    private QHUserService qhUserService;

    @Test
    public void findUserByLoginUsernameTest(){
        QHUser qhUser = qhUserService.findUserByLoginUsername("1246874542@qq.com");
        System.out.println(qhUser);
    }
}
