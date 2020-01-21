package com.coupon.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ReferralTransactionEntity;

public interface ReferralTransactionRepository extends PagingAndSortingRepository<ReferralTransactionEntity, Integer> {
    List<ReferralTransactionEntity> findByUserId(String userId);
}
