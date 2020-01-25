package com.coupon.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coupon.exceptions.ResourceNotFoundException;
import com.coupon.service.mapper.ServiceMapper;
import com.coupon.user.bean.Role;
import com.coupon.user.bean.User;
import com.coupon.user.bean.jpa.UserEntity;
import com.coupon.user.repository.UserRepository;
import com.coupon.user.service.UserRoleService;
import com.coupon.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ServiceMapper<User, UserEntity> userServiceMapper;
    @Resource
    private UserRoleService userRoleService;
    @Override
    public User findByEmail(final String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        return userServiceMapper.mapEntityToDTO(userEntity, User.class);
    }

    @Override
    public User findById(final Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        User user = userServiceMapper.mapEntityToDTO(userEntity, User.class);

        return user;
    }

    @Override
    public User findUserWithRolesById(final Integer id) {
        User user = this.findById(id);

        if (user != null) {
            user.setRoles(this.userRoleService.findAllByUserId(id));
        }

        return user;
    }
}
