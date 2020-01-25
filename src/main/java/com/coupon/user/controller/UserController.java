package com.coupon.user.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.auth.security.CurrentUser;
import com.coupon.auth.security.UserPrincipal;
import com.coupon.user.bean.User;
import com.coupon.user.service.UserService;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/me")
    public User getCurrentUser(@CurrentUser final UserPrincipal userPrincipal) {
        return userService.getUserDetailsById(userPrincipal.getId());
    }
}
