package com.coupon.user.service;

import com.coupon.user.bean.User;

public interface UserService {
    User findByEmail(String email);

    User findById(Integer id);

    User findUserWithRolesById(Integer id);
}
