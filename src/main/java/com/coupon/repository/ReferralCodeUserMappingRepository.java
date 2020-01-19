package com.coupon.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ReferralCodeUserMappingEntity;

public interface ReferralCodeUserMappingRepository extends PagingAndSortingRepository<ReferralCodeUserMappingEntity, Integer> {
    List<ReferralCodeUserMappingEntity> findByUserId(String user_id);

    List<ReferralCodeUserMappingEntity> findByReferrerCode(String referral_code);
}
