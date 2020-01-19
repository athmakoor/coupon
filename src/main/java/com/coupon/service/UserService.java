package com.coupon.service;

import com.coupon.bean.CreateUserRequest;
import com.coupon.bean.CreateUserResponse;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest requestBody);
}
