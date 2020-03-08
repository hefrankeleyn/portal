package com.hef.qhjiaoyiyuan.service.impl;

import com.hef.qhjiaoyiyuan.bean.QHUser;
import com.hef.qhjiaoyiyuan.dao.QHUserDao;
import com.hef.qhjiaoyiyuan.service.QHUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/3/2
 * @Author lifei
 */
@Service
public class QHUserServiceImpl implements QHUserService {

    private QHUserDao qhUserDao;

    @Autowired(required = false)
    public void setQhUserDao(QHUserDao qhUserDao) {
        this.qhUserDao = qhUserDao;
    }

    @Override
    public List<QHUser> findAllQHUser() {
        List<QHUser> allQHUser = qhUserDao.findAllQHUser();
        return allQHUser;
    }

    @Override
    public QHUser findUserByLoginUsername(String loginUsername) {
        return qhUserDao.findQHUserByLoginUsername(loginUsername);
    }
}
