package com.coupon.user.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coupon.user.bean.jpa.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
