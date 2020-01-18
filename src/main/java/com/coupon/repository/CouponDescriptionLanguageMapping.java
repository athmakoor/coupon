package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CouponDescriptionLanguageMappingEntity;

public interface CouponDescriptionLanguageMapping extends PagingAndSortingRepository<CouponDescriptionLanguageMappingEntity, Integer> {
}
