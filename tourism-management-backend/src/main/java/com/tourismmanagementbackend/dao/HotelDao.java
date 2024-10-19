package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.HotelDto;
import com.tourismmanagementbackend.entity.Hotel;

import java.util.List;

public interface HotelDao {
    /**
     * 查询酒店列表，根据酒店名字就行查询
     */
    List<Hotel> getHotelsByHotelNameList( HotelDto hotelDto);
    /**
     * 查询酒店总数
     */
    int countPage(HotelDto hotelDto);
    /**
     * 新增酒店
     */
    int addHotel(Hotel hotel);
    /**
     * 删除酒店
     */
    int deleteHotel(String hotelName);
    /**
     * 修改酒店
     */
    int updateHotel(Hotel hotel);

    double getRatingByHotelName(String hotelName);
    List<Hotel> getHotelByScenicAreaName(Hotel hotel);

    Hotel getHotelNum(Hotel hotel);

}
