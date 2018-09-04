package com.ganglion.service.impl;

import com.ganglion.entity.EmplRole;
import com.ganglion.entity.User;
import com.ganglion.mapper.EmplRoleMapper;
import com.ganglion.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsServiceImpl extends BaseService<User> implements UserDetailsService {

    @Autowired
    private EmplRoleMapper emplRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.selectByKey(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
        } else {
            List<EmplRole> roleList = emplRoleMapper.getRoleListByEmpl(user.getEmplID());
            List<GrantedAuthority> authorities =  roleList.stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getRoleID()))
                    .collect(Collectors.toList());
            return new JwtUser(user.getUserName(),user.getUserPwd(),authorities, user.getUserLastLogon(),user.getEmplID());
        }
    }
}
