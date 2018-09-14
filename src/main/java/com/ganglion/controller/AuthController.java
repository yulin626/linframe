package com.ganglion.controller;

import com.ganglion.model.JwtAuthenticationRequest;
import com.ganglion.model.UserDTO;
import com.ganglion.msg.JwtAuthenticationResponse;
import com.ganglion.msg.ResultResponse;
import com.ganglion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@EnableAutoConfiguration
public class AuthController {
    @Value("Authorization")//("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @CrossOrigin
    @PostMapping("/auth")
    public ResultResponse<JwtAuthenticationResponse> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        return authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    @CrossOrigin
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResultResponse<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        return authService.refresh(token);
    }

    @CrossOrigin
    @PostMapping("/auth/register")
    public ResultResponse createAuthenticationToken(@RequestBody UserDTO userDTO) throws AuthenticationException {
        return authService.register(userDTO);
    }
}
