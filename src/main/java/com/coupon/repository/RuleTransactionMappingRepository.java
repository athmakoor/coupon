package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.RuleTransactionMappingEntity;

public interface RuleTransactionMappingRepository extends PagingAndSortingRepository<RuleTransactionMappingEntity, Integer> {
}
