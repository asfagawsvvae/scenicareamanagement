package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.VisitorinfoDto;
import com.tourismmanagementbackend.entity.Visitorinfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 游客信息表，全(Visitorinfo)表服务接口
 *
 * @author makejava
 * @since 2024-07-13 16:03:33
 */
public interface VisitorinfoService {
    List<Visitorinfo> getVisitorinfoByVisitorNameList(VisitorinfoDto visitorinfoDto);
    int countPage(VisitorinfoDto visitorinfoDto);
    int deleteVisitorinfo(String username);

    int getVisitorParkingNum(Visitorinfo visitorinfo);

    int getVisitorHotelParkingNum(Visitorinfo visitorinfo);

}
