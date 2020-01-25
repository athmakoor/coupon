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
import com.coupon.service.EndUserService;

@RestController
public class ApiController {
	@Resource
	private CartService cartService;
	@Resource
	private ConversionService conversionService;
	@Resource
	private EndUserService endUserService;

	@PostMapping("/checkoutapi")
	CartResponse requestInfo(@RequestBody final CartRequest requestBody) {
		return cartService.getCartResponse(requestBody);
	}

	@PostMapping("/conversionapi")
	ConversionResponse saveCart(@RequestBody final ConversionRequest requestBody) {
		return conversionService.saveConversions(requestBody);
	}

	@PostMapping("/create-user")
	CreateUserResponse createUser(@RequestBody final CreateUserRequest requestBody) {
		return endUserService.createUser(requestBody);
	}
}
