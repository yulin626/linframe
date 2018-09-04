package com.ganglion.service.impl;

import com.ganglion.constant.CommonConstants;
import com.ganglion.entity.User;
import com.ganglion.mapper.UserMapper;
import com.ganglion.model.JwtUser;
import com.ganglion.model.UserDTO;
import com.ganglion.msg.JwtAuthenticationResponse;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.AuthService;
import com.ganglion.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl extends BaseService<User> implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆
     */
    @Override
    public ResultResponse<JwtAuthenticationResponse> login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(token);
        ResultResponse result = new ResultResponse();
        result.setData(response);
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }

    /**
     * 刷新token
     */
    @Override
    public ResultResponse<JwtAuthenticationResponse> refresh(String oldToken) {
        ResultResponse result = new ResultResponse();
        final String token = oldToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            String newToken= jwtTokenUtil.refreshToken(token);

            JwtAuthenticationResponse response = new JwtAuthenticationResponse(newToken);
            result.setData(response);
            result.setStatus(1);
            result.setMessage(CommonConstants.SUCCESS);
            return result;
        }
        result.setStatus(0);
        result.setMessage(CommonConstants.ERROR);
        return result;
    }

    @Override
    public ResultResponse register(UserDTO userDTO) {
        final String username = userDTO.getUserName();
        if(userMapper.findByUsername(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDTO.getUserPwd();

        User userToAdd = new User();
        userToAdd.setUserName(userDTO.getUserName());
        userToAdd.setUserPwd(encoder.encode(rawPassword));
        userToAdd.setUserPwdLastChangeTime(new Date());
        userToAdd.setEmplID("100001");
        userToAdd.setUerLogonTimes(0);
        userToAdd.setUserNeedChgPwd(0);
        userToAdd.setUserDisabled(0);
        userToAdd.setCreateTime(new Date());
        userToAdd.setUpdateTime(new Date());
        this.insert(userToAdd);

        ResultResponse result = new ResultResponse();
        result.setStatus(1);
        result.setMessage(CommonConstants.SUCCESS);
        return result;
    }
}
