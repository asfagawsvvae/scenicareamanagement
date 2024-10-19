package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Vehicle)实体类
 *
 * @author makejava
 * @since 2024-07-14 15:22:12
 *
 */

@Data
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 472507987275511999L;

    private String vehicleId;

    private String vehicleNumber;

    private String vehicleType;

    private String agency;

    private String state;
}

