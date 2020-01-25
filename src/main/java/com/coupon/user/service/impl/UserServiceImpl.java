package com.coupon.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.auth.payload.SignUpRequest;
import com.coupon.exceptions.ResourceNotFoundException;
import com.coupon.service.mapper.ServiceMapper;
import com.coupon.user.bean.Role;
import com.coupon.user.bean.User;
import com.coupon.user.bean.jpa.RoleEntity;
import com.coupon.user.bean.jpa.UserEntity;
import com.coupon.user.bean.jpa.UserRoleEntity;
import com.coupon.user.repository.UserRepository;
import com.coupon.user.repository.UserRoleRepository;
import com.coupon.user.service.UserRoleService;
import com.coupon.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ServiceMapper<User, UserEntity> userServiceMapper;
    @Resource
    private ServiceMapper<Role, RoleEntity> roleServiceMapper;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleRepository userRoleRepository;

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

    @Override
    @Transactional
    public User create(final User user) {
        user.setId(null);
        UserEntity userEntity = new UserEntity();
        userServiceMapper.mapDTOToEntity(user, userEntity);
        UserEntity userEntitySaved = userRepository.save(userEntity);
        User savedUser = userServiceMapper.mapEntityToDTO(userEntitySaved, User.class);

        return savedUser;
    }

    @Override
    @Transactional
    public User signUpUser(final SignUpRequest signUpRequest) {
        User user = new User();
        UserRoleEntity userRoleEntity;
        List<UserRoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity;
        UserEntity userEntity = new UserEntity();

        user.setFirstName(signUpRequest.getFirst_name());
        user.setLastName(signUpRequest.getLast_name());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = this.create(user);
        userEntity.setId(user.getId());

        for (Integer roleId: signUpRequest.getRole_ids()) {
            userRoleEntity = new UserRoleEntity();
            roleEntity = new RoleEntity();
            roleEntity.setId(roleId);
            userRoleEntity.setRole(roleEntity);
            userRoleEntity.setUser(userEntity);
            roleEntities.add(userRoleEntity);
        }

        userRoleRepository.saveAll(roleEntities);

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserDetailsById(Integer id) {
        return this.findUserWithRolesById(id);
    }
}
