package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.HotelDao;
import com.tourismmanagementbackend.dto.HotelDto;
import com.tourismmanagementbackend.entity.Hotel;
import com.tourismmanagementbackend.service.HotelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: HotelServiceImpl
 * @Package com.tourismmanagementbackend.service.impl
 * @Description:
 * @date 2024/7/11 21:29
 */

@Service("hotelService")
public class HotelServiceImpl implements HotelService {
    @Resource
    private HotelDao hotelDao;

    @Override
    public List<Hotel> getHotelsByHotelNameList(HotelDto hotelDto){
        return hotelDao.getHotelsByHotelNameList(hotelDto);
    }

    @Override
    public int countPage(HotelDto hotelDto){
        return hotelDao.countPage(hotelDto);
    }

    @Override
    public int addHotel(Hotel hotel) {
        return hotelDao.addHotel(hotel);
    }

    @Override
    public int deleteHotel(String hotelName) {
        return hotelDao.deleteHotel(hotelName);
    }

    @Override
    public int updateHotel(Hotel hotel) {
        return hotelDao.updateHotel(hotel);
    }

    @Override
    public double getRatingByHotelName(String hotelName) {
        return hotelDao.getRatingByHotelName(hotelName);
    }
    @Override
    public List<Hotel> getHotelByScenicAreaName(Hotel hotel) {return hotelDao.getHotelByScenicAreaName(hotel);}

    @Override
    public  Hotel getHotelNum(Hotel hotel) {return hotelDao.getHotelNum(hotel);}
}
