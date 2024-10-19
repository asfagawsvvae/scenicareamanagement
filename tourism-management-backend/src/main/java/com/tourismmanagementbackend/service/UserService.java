package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.LoginUserDto;
import com.tourismmanagementbackend.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2024-07-13 02:09:34
 */
public interface UserService {

    LoginUserDto logUser(User user);

    int registerUser(User user);

    User selectUserByUsername(User user);

    int updateUser(User user);

    int updatePassword(User user);
}