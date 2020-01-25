package com.coupon.user.service;

import org.springframework.transaction.annotation.Transactional;

import com.coupon.auth.payload.SignUpRequest;
import com.coupon.user.bean.User;

public interface UserService {
    User findByEmail(String email);

    User findById(Integer id);

    User findUserWithRolesById(Integer id);

    @Transactional
    User create(User user);

    @Transactional
    User signUpUser(SignUpRequest signUpRequest);

    boolean existsByEmail(String email);

    User getUserDetailsById(Integer id);
}
