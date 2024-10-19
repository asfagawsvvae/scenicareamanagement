package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.entity.User;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-13 02:09:34
 */
public interface UserDao {

    // 用户登录
    User loginUser(User user);

    // 用户注册
    int registerUser(User user);

    // 根据用户名查询用户个人信息
    User selectUserByUsername(User user);

    // 修改个人信息
    int updateUser(User user);

    // 修改密码
    int updatePassword(User user);
}