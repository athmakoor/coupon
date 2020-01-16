package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.StaticEventFieldEntity;
import com.coupon.event.constants.EventStatus;

public interface StaticEventFieldRepository extends PagingAndSortingRepository<StaticEventFieldEntity, Integer> {
    Iterable<StaticEventFieldEntity> findByEventCodeRefAndStatus(String eventCode, EventStatus active);
}
