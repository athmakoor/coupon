package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponProductRestrictionsEntity;

public interface CouponProductRestrictionsrepository extends PagingAndSortingRepository<CouponProductRestrictionsEntity, Integer> {
}
