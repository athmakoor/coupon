package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponCodeLanguageMappingEntity;

public interface CouponCodeLanguageMappingRepository extends PagingAndSortingRepository<CouponCodeLanguageMappingEntity, Integer> {
}
