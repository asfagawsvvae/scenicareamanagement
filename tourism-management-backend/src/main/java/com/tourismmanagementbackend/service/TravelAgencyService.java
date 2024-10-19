package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.TravelAgencyDto;
import com.tourismmanagementbackend.entity.TravelAgency;

import java.util.List;

/**
 * (Travelagency)表服务接口
 *
 * @author makejava
 * @since 2024-07-12 15:08:32
 */
public interface TravelAgencyService {
    List<TravelAgency> getTravelAgencyByNameList(TravelAgencyDto travelAgencyDto);

    int countPage(TravelAgencyDto travelAgencyDto);

    int addTravelAgency(TravelAgency travelAgency);

    int deleteTravelAgency(String travelAgencyName);

    int updateTravelAgency(TravelAgency travelAgency);


    List<TravelAgency> getAgencyNameList(TravelAgency travelAgency);

    List<TravelAgency> getAgencyNameList_1();

}
