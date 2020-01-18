package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponEntity;

public interface CouponDetailsTableRepository extends PagingAndSortingRepository<CouponEntity, Integer> {
}
