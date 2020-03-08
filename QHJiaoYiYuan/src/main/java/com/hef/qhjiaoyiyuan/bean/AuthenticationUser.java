package com.hef.qhjiaoyiyuan.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Date 2020/3/8
 * @Author lifei
 */
public class AuthenticationUser implements UserDetails {

    private QHUser qhUser;

    public AuthenticationUser(QHUser qhUser){
        this.qhUser = qhUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<QHRole> roles = qhUser.getRoles();
        if (CollectionUtils.isEmpty(roles)) {
            List<GrantedAuthority> authorities = new ArrayList<>();

            return null;
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (QHRole role : roles) {
            authorityList.add(new SimpleGrantedAuthority(role.getRoleType()));
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return qhUser.getLoginPassWorld();
    }

    @Override
    public String getUsername() {
        return qhUser.getLoginUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
