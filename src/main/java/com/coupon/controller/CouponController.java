package com.coupon.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.coupon.service.CouponService;

@RestController
public class CouponController {
    @Resource
    private CouponService couponService;


}
