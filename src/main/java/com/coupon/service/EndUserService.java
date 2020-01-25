package com.coupon.service;

import com.coupon.bean.CreateUserRequest;
import com.coupon.bean.CreateUserResponse;

public interface EndUserService {
    CreateUserResponse createUser(CreateUserRequest requestBody);
}
