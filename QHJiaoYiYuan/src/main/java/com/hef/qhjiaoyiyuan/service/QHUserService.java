package com.hef.qhjiaoyiyuan.service;

import com.hef.qhjiaoyiyuan.bean.QHUser;

import java.util.List;

public interface QHUserService {

    List<QHUser> findAllQHUser();

    /**
     * 根据登陆名查找用户
     * @param loginUsername
     * @return
     */
    QHUser findUserByLoginUsername(String loginUsername);
}
