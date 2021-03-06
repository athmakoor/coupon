package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.CustomEventsEntity;
import com.coupon.event.constants.EventStatus;

public interface StaticCustomEventRepository extends PagingAndSortingRepository<CustomEventsEntity, Integer> {

    Iterable<CustomEventsEntity> findByStatus(EventStatus name);
}
