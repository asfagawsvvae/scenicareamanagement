package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.LoginUserDto;
import com.tourismmanagementbackend.entity.User;
import com.tourismmanagementbackend.service.UserService;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2024-07-13 02:09:34
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    // 用户登录
    @RequestMapping("/login")
    public DataResult login(@RequestBody User user) {
        LoginUserDto loginUserDto = userService.logUser(user);
        if (loginUserDto != null) {
            return DataResult.successByData(loginUserDto);
        }
        return DataResult.errByErrCode(Code.LOGIN_ERROR);
    }

    // 用户注册
    @RequestMapping("/register")
    public DataResult register(@RequestBody User user) {
        try{
            int i=userService.registerUser(user);
            if (i > 0) {
                return DataResult.succ();
            }else {
                return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
            }
        }catch (Exception e) {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    // 根据用户名查询用户个人信息
    @RequestMapping("/selectUserByUsername")
    public DataResult selectUserByUsername(@RequestBody User user){
        return DataResult.successByData(userService.selectUserByUsername(user));
    }

    // 修改个人信息
    @RequestMapping("/updateUser")
    public DataResult updateUser(@RequestBody User user){
        int i = userService.updateUser(user);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.UPDATE_USERINFO_ERROR);
        }
    }

    // 修改密码
    @RequestMapping("/updatePassword")
    public DataResult updatePassword(@RequestBody User user){
        int i = userService.updatePassword(user);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.UPDATE_PASSWORD_ERROR);
        }
    }
}