package com.coupon.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.RulesDetailsEntity;

public interface RulesDetailsRepository extends PagingAndSortingRepository<RulesDetailsEntity, Integer> {
    List<RulesDetailsEntity> findByType(String type);

    List<RulesDetailsEntity> findByTypeIn(List<String> types);

    List<RulesDetailsEntity> findByTypeAndItemNameIn(String coupon, List<String> types);
}
