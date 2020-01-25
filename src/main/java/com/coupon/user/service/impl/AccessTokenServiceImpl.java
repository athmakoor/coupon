package com.coupon.user.service.impl;

import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.coupon.exceptions.ResourceNotFoundException;
import com.coupon.service.mapper.ServiceMapper;
import com.coupon.user.bean.AccessToken;
import com.coupon.user.bean.jpa.AccessTokenEntity;
import com.coupon.user.repository.AccessTokenRepository;
import com.coupon.user.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    @Resource
    private AccessTokenRepository accessTokenRepository;
    @Resource
    private ServiceMapper<AccessToken, AccessTokenEntity> accessTokenServiceMapper;
    @Override
    public AccessToken save(AccessToken accessToken) {
        return null;
    }

    @Override
    public AccessToken findByToken(String token) {
        Optional<AccessTokenEntity> optionalAccessToken = accessTokenRepository.findByToken(token);

        if (!optionalAccessToken.isPresent()) {
            throw new ResourceNotFoundException("Access Token", "token", token);
        }

        AccessToken accessToken = accessTokenServiceMapper.mapEntityToDTO(optionalAccessToken.get(), AccessToken.class);

        return accessToken;
    }

    @Override
    public AccessToken findWithUserByToken(String token) {
        return null;
    }

    @Override
    public void setAuthenticationByUserDetails(final HttpServletRequest request, final UserDetails userDetails) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
