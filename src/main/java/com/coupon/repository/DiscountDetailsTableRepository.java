package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.DiscountDetailsTableEntity;

public interface DiscountDetailsTableRepository extends PagingAndSortingRepository<DiscountDetailsTableEntity, Integer> {
}
