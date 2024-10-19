package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.ScenicAreaDto;
import com.tourismmanagementbackend.entity.ScenicArea;

import java.util.List;

/**
 * (Scenicarea)表服务接口
 *
 * @author makejava
 * @since 2024-07-11 23:47:55
 */
public interface ScenicAreaService {


    List<ScenicArea> getScenicAreaList(ScenicAreaDto scenicAreaDto);

    int deleteScenicArea(String scenicAreaName);

    int addScenicArea(ScenicArea scenicArea);

    int updateScenicArea(ScenicArea scenicArea);

    List<ScenicArea> getScenicAreaListByName(String scenicAreaName);

    int countPage(ScenicAreaDto scenicAreaDto);

    List<ScenicArea> getScenicInfo();
    List<ScenicArea> getScenicInfo_1();

    double getRateByName(String scenicAreaName);


}
