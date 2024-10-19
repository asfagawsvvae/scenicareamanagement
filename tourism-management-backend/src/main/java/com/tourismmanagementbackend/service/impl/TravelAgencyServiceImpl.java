package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.TravelAgencyDao;
import com.tourismmanagementbackend.dto.TravelAgencyDto;
import com.tourismmanagementbackend.entity.TravelAgency;
import com.tourismmanagementbackend.service.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Travelagency)表服务实现类
 *
 * @author makejava
 * @since 2024-07-12 15:08:32
 */
@Service("travelAgencyService")
public class TravelAgencyServiceImpl implements TravelAgencyService {

    @Autowired
    private TravelAgencyDao travelAgencyDao;
    @Override
    public List<TravelAgency> getTravelAgencyByNameList(TravelAgencyDto travelAgencyDto) {
        return travelAgencyDao.getTravelAgencyByNameList(travelAgencyDto);
    }

    @Override
    public int countPage(TravelAgencyDto travelAgencyDto) {
        return travelAgencyDao.countPage(travelAgencyDto);
    }

    @Override
    public int addTravelAgency(TravelAgency travelAgency) {
        return travelAgencyDao.addTravelAgency(travelAgency);
    }

    @Override
    public int deleteTravelAgency(String travelAgencyName) {
        return travelAgencyDao.deleteTravelAgency(travelAgencyName);
    }

    @Override
    public int updateTravelAgency(TravelAgency travelAgency) {
        return travelAgencyDao.updateTravelAgency(travelAgency);
    }

    @Override
    public List<TravelAgency> getAgencyNameList(TravelAgency travelAgency) {

        return travelAgencyDao.getAgencyNameList(travelAgency);
    }


    @Override
    public List<TravelAgency> getAgencyNameList_1() {

        return travelAgencyDao.getAgencyNameList_1();
    }
}
