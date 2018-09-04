package com.ganglion.service;

import io.jsonwebtoken.Claims;

public interface JWTService {
    Claims parseJWT(String jsonWebToken);

    String createJWT(String username, String roles, String privileges);
}
