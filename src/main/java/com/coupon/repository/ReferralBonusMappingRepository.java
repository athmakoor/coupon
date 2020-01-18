package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ReferralBonusMappingEntity;

public interface ReferralBonusMappingRepository extends PagingAndSortingRepository<ReferralBonusMappingEntity, Integer> {
}
