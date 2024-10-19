package com.tourismmanagementbackend.service.impl;

import com.alibaba.fastjson.JSON;
import com.tourismmanagementbackend.dao.MenuDao;
import com.tourismmanagementbackend.dao.UserDao;
import com.tourismmanagementbackend.dto.LoginUserDto;
import com.tourismmanagementbackend.entity.Menu;
import com.tourismmanagementbackend.entity.User;
import com.tourismmanagementbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Year;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-07-13 02:09:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private MenuDao menuDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public LoginUserDto logUser(User user){
        User loginUser = userDao.loginUser(user);
        if(null!=loginUser){
            LoginUserDto loginUserDto = new LoginUserDto();
            loginUser.setPassword("");
            loginUserDto.setUser(loginUser);
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set("Token::"+token, JSON.toJSONString(loginUser),600, TimeUnit.MINUTES);
            loginUserDto.setToken(token);
//            List<Menu> menus = menuDao.getMenusByUserType(loginUser);
//            loginUserDto.setMenus(menus);
            return loginUserDto;
        }
        return null;
    }
    @Override
    public int registerUser(User user) {
        return userDao.registerUser(user);
    }
    @Override
    public User selectUserByUsername(User user) {
        return userDao.selectUserByUsername(user);
    }
    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }

}