package com.hef.qhjiaoyiyuan.dao;

import com.hef.qhjiaoyiyuan.bean.QHUser;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

/**
 * @Date 2020/3/3
 * @Author lifei
 */
@SpringBootTest
public class QHUserDaoTest {

    @Autowired(required = false)
    private QHUserDao qhUserDao;

    @Test
    public void findAllQHUsers(){
        List<QHUser> allQHUser = qhUserDao.findAllQHUser();
        System.out.println(allQHUser);
    }

}
