package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ConversionDataEntity;

public interface ConversionDataRepository extends PagingAndSortingRepository<ConversionDataEntity, Integer> {
}
