package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Cablecar)实体类
 *
 * @author makejava
 * @since 2024-07-13 21:38:07
 */
@Data
public class Cablecar implements Serializable {
    private static final long serialVersionUID = 889978981926997846L;

    private String cableCarName;

    private String startTime;

    private String endTime;

    private String intervalTime;

    private String location;

    private String cableCarCode;


}

