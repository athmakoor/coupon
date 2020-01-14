package com.coupon.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.ConversionDataEntity;

public interface ConversionDataRepository extends PagingAndSortingRepository<ConversionDataEntity, Integer> {
    List<ConversionDataEntity> findByUserId(String userId);
}
