package com.ganglion.service;

import com.ganglion.model.UserDTO;
import com.ganglion.msg.JwtAuthenticationResponse;
import com.ganglion.msg.ResultResponse;

public interface AuthService {
    ResultResponse register(UserDTO userToAdd);
    ResultResponse<JwtAuthenticationResponse> login(String username, String password);
    ResultResponse<JwtAuthenticationResponse> refresh(String oldToken);
}
