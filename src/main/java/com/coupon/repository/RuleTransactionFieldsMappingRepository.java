package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.RuleTransactionFieldsMappingEntity;

public interface RuleTransactionFieldsMappingRepository extends PagingAndSortingRepository<RuleTransactionFieldsMappingEntity, Integer> {
}
