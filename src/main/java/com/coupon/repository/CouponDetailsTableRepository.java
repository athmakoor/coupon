package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponDetailsTableEntity;

public interface CouponDetailsTableRepository extends PagingAndSortingRepository<CouponDetailsTableEntity, Integer> {
}
