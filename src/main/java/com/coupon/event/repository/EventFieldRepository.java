package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.EventFieldEntity;

public interface EventFieldRepository  extends PagingAndSortingRepository<EventFieldEntity, Integer> {
}
