package com.coupon.service;

import com.coupon.bean.CouponRequestDTO;
import com.coupon.bean.CouponResponseDTO;

public interface CouponService {
    CouponResponseDTO create(CouponRequestDTO data);
}
