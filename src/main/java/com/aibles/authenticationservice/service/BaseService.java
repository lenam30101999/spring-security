package com.aibles.authenticationservice.service;

import com.aibles.authenticationservice.jwt.JwtTokenProvider;
import com.aibles.authenticationservice.mapper.ModelMapper;
import com.aibles.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class BaseService {
    @Autowired protected UserRepository userRepository;
    @Autowired protected PasswordEncoder passwordEncoder;
    @Autowired protected AuthenticationManager authenticationManager;
    @Autowired protected JwtTokenProvider tokenProvider;
    @Autowired protected ModelMapper modelMapper;
    @Autowired protected CacheManager cacheManager;

    @Value("${app.jwtSecret.token}")
    protected String secretKey;

    protected Integer getJwt(String accessToken) {
        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ")) {
            String jwt = accessToken.substring(7);
            return tokenProvider.getIdFromSubjectJWT(jwt);
        }
        return null;
    }
}
