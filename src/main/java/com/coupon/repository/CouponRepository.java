package com.coupon.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.coupon.bean.jpa.CouponEntity;

public interface CouponRepository extends PagingAndSortingRepository<CouponEntity, Integer> {
    @Query("SELECT e FROM CouponEntity e WHERE e.startedOn < :currentDate AND (e.closedOn IS NULL OR e.closedOn > :currentDate) " +
            "AND (e.minCartValue is NULL or (e.minCartValue * :margin) < :cartValue)")
    Iterable<CouponEntity> findAllValid(@Param("currentDate") Date date, @Param("cartValue") Double cartValue, @Param("margin") Double margin);
}
