package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.CustomEventFieldEntity;
import com.coupon.event.constants.EventStatus;

public interface StaticEventFieldRepository extends PagingAndSortingRepository<CustomEventFieldEntity, Integer> {
    Iterable<CustomEventFieldEntity> findByEventCodeRefAndStatus(String eventCode, EventStatus active);
}
