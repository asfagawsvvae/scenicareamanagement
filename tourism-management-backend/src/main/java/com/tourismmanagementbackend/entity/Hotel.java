package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: Hotel
 * @Package com.tourismmanagementbackend.entity
 * @Description: 酒店
 * @date 2024/7/11 20:58
 */

@Data
public class Hotel implements Serializable {
    private int hotelId;
    private String hotelName;
    private String address;
    private int maxCapacity;
    private int parkingSpaces;
    private int occupiedRooms;
    private int maxRooms;
    private String date;
    private String location;
    private String hotelCode;
}
