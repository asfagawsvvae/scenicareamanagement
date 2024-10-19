package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.HotelDto;
import com.tourismmanagementbackend.entity.Hotel;

import java.util.List;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: HotelService
 * @Package com.tourismmanagementbackend.service
 * @Description:
 * @date 2024/7/11 21:29
 */

public interface HotelService {
    List<Hotel> getHotelsByHotelNameList(HotelDto hotelDto);
    int countPage(HotelDto hotelDto);
    int addHotel(Hotel hotel);
    int deleteHotel(String hotelName);
    int updateHotel(Hotel hotel);
    double getRatingByHotelName(String hotelName);
    List<Hotel> getHotelByScenicAreaName(Hotel hotel);

    Hotel getHotelNum(Hotel hotel);
}
