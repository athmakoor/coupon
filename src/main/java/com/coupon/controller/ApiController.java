package com.coupon.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.bean.CartRequest;
import com.coupon.bean.CartResponse;
import com.coupon.bean.ConversionRequest;
import com.coupon.bean.ConversionResponse;
import com.coupon.bean.CreateUserRequest;
import com.coupon.bean.CreateUserResponse;
import com.coupon.service.CartService;
import com.coupon.service.ConversionService;
import com.coupon.service.UserService;

@RestController
public class ApiController {
	@Resource
	private CartService cartService;
	@Resource
	private ConversionService conversionService;
	@Resource
	private UserService userService;

	@PostMapping("/checkout")
	CartResponse requestInfo(@RequestBody final CartRequest requestBody) {
		return cartService.getCartResponse(requestBody);
	}

	@PostMapping("/conversion")
	ConversionResponse saveCart(@RequestBody final ConversionRequest requestBody) {
		return conversionService.saveConversions(requestBody);
	}

	@PostMapping("/create-user")
	CreateUserResponse createUser(@RequestBody final CreateUserRequest requestBody) {
		return userService.createUser(requestBody);
	}

	@PostMapping("/coupon-details")
	CreateUserResponse getCouponDetails(@RequestBody final CreateUserRequest requestBody) {
		return userService.createUser(requestBody);
	}
}
