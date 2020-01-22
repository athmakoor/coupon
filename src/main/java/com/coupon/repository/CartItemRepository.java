package com.coupon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.bean.jpa.CartItemEntity;

public interface CartItemRepository extends PagingAndSortingRepository<CartItemEntity, Integer> {
}
