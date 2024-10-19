package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.HotelDto;
import com.tourismmanagementbackend.entity.Hotel;
import com.tourismmanagementbackend.service.HotelService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: HotelController
 * @Package com.tourismmanagementbackend.controller
 * @Description:
 * @date 2024/7/11 21:43
 */

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Resource
    private HotelService hotelService;

    @RequestMapping("getHotelsByHotelNameList")
    public DataResult getHotelsByHotelNameList(@RequestBody HotelDto hotelDto){
        hotelDto.setPage(PageUtils.getStartPage(hotelDto.getPage(),hotelDto.getLimit()));
        List<Hotel> hotelsByNameList = hotelService.getHotelsByHotelNameList(hotelDto);
        long count = hotelService.countPage(hotelDto);
        return DataResult.successByTotalData(hotelsByNameList,count);
    }

    @RequestMapping("addHotel")
    public DataResult addHotel(@RequestBody Hotel hotel){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        hotel.setHotelCode(uuid);
        int i = hotelService.addHotel(hotel);
        if(i>0){
            return DataResult.succ();
        }else{
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("deleteHotel/{hotelName}")
    public DataResult deleteHotel(@PathVariable("hotelName") String hotelName){
        int i = hotelService.deleteHotel(hotelName);
        if(i>0){
            return DataResult.succ();
        }else{
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateHotel")
    public DataResult updateHotel(@RequestBody Hotel hotel){
        int i = hotelService.updateHotel(hotel);
        if(i>0){
            return DataResult.succ();
        }else{
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }

    @RequestMapping("getRatingByHotelName/{hotelName}")
    public DataResult getRatingByHotelName(@PathVariable("hotelName") String hotelName ){
        return DataResult.successByDatas(hotelService.getRatingByHotelName(hotelName));
    }
    @RequestMapping("getHotelByScenicAreaName")
    public DataResult getHotelByScenicAreaName(@RequestBody Hotel hotel){
        List<Hotel> hotels = hotelService.getHotelByScenicAreaName(hotel);
        return DataResult.successByDataArray(hotels);
    }


    @RequestMapping("getHotelNum")
    public DataResult getHotelNum(@RequestBody Hotel hotel){
        return DataResult.successByDatas(hotelService.getHotelNum(hotel));
    }

}
