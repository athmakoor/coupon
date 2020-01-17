package com.coupon.event.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.event.bean.jpa.EventFieldsEntity;

public interface EventFieldRepository  extends PagingAndSortingRepository<EventFieldsEntity, Integer> {
}
