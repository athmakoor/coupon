package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponDescriptionLanguageMappingEntity;

public interface CouponDescriptionLanguageMappingRepository extends PagingAndSortingRepository<CouponDescriptionLanguageMappingEntity, Integer> {
}
