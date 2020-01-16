package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.StaticCustomEventEntity;
import com.coupon.event.constants.EventStatus;

public interface StaticCustomEventRepository extends PagingAndSortingRepository<StaticCustomEventEntity, Integer> {

    Iterable<StaticCustomEventEntity> findByStatus(EventStatus name);
}
