package com.coupon.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coupon.service.mapper.ServiceMapper;
import com.coupon.user.bean.Role;
import com.coupon.user.bean.UserRole;
import com.coupon.user.bean.jpa.RoleEntity;
import com.coupon.user.bean.jpa.UserRoleEntity;
import com.coupon.user.repository.UserRoleRepository;
import com.coupon.user.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private ServiceMapper<Role, RoleEntity> roleServiceMapper;

    @Override
    public List<Role> findAllByUserId(Integer id) {
        List<UserRoleEntity>  userRoleEntities = userRoleRepository.findByUserId(id);
        List<Role> roles = new ArrayList<>();

        for (UserRoleEntity userRole : userRoleEntities) {
            roles.add(roleServiceMapper.mapEntityToDTO(userRole.getRole(), Role.class));
        }

        return roles;
    }
}
