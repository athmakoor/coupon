package com.coupon.service;

import com.coupon.bean.CartRequest;
import com.coupon.bean.CartResponse;

public interface CartService {
    CartResponse getCartResponse(CartRequest cartRequest);
}
