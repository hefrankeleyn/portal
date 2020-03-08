package com.hef.qhjiaoyiyuan.service.impl;

import com.hef.qhjiaoyiyuan.bean.AuthenticationUser;
import com.hef.qhjiaoyiyuan.bean.QHUser;
import com.hef.qhjiaoyiyuan.service.QHUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户权限认证
 * @Date 2020/3/8
 * @Author lifei
 */
@Service(value = "userDetailsService")
public class AuthenticationServiceImpl implements UserDetailsService {


    private QHUserService qhUserService;

    @Autowired
    public void setQhUserService(QHUserService qhUserService) {
        this.qhUserService = qhUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QHUser qhUser = qhUserService.findUserByLoginUsername(username);
        if (qhUser!=null){
            return new AuthenticationUser(qhUser);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }
}
