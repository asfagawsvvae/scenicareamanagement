package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.VisitorinfoDto;
import com.tourismmanagementbackend.entity.Visitorinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 游客信息表，全(Visitorinfo)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-13 16:03:27
 */
public interface VisitorinfoDao {

    List<Visitorinfo> getVisitorinfoByVisitorNameList(VisitorinfoDto visitorinfoDto);

    int countPage(VisitorinfoDto visitorinfoDto);

    int deleteVisitorinfo(String username);
    int getVisitorParkingNum(Visitorinfo visitorinfo);  //得到不住酒店的

    int getVisitorHotelParkingNum  (Visitorinfo visitorinfo)  ;                   //得到住酒店的

}

