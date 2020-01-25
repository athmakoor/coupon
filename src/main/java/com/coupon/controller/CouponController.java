package com.coupon.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.bean.CouponRequestDTO;
import com.coupon.bean.CouponResponseDTO;
import com.coupon.service.CouponService;

@RestController
public class CouponController {
    @Resource
    private CouponService couponService;

    @PostMapping("/coupon/create")
    CouponResponseDTO createCoupon(@RequestBody final CouponRequestDTO requestBody) {
        return couponService.create(requestBody);
    }


}
