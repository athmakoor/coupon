package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ConverstionCartDataEntity;

public interface ConversionCartDataRepository extends PagingAndSortingRepository<ConverstionCartDataEntity, Integer> {
}
