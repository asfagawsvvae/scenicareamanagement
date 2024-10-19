package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-07-13 02:09:34
 */
@Data

public class User implements Serializable {
    private static final long serialVersionUID = 767273214103861452L;

    private Integer userId;

    private String username;

    private String password;

    private String phone;

    private String idCard;
/**
     * 1普通用户，2普管理员
     */
    private Integer userType;

    private String email;

    private String birth;

    private String location;

    private String sign;

}