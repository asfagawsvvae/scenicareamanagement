package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.TourguideDto;
import com.tourismmanagementbackend.entity.Tourguide;
import com.tourismmanagementbackend.dao.TourguideDao;
import com.tourismmanagementbackend.service.TourguideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tourguide)表服务实现类
 *
 * @author makejava
 * @since 2024-07-11 19:43:38
 */
@Service("tourguideService")
public class TourguideServiceImpl implements TourguideService {


    @Autowired
    private  TourguideDao TourguideDao;
    /**
     * 寻找在职导游
     */
    @Override
    public List<Tourguide> getTourguideByNameList(TourguideDto tourguideDto) {
        return TourguideDao.getTourguideByNameList(tourguideDto);
    }

    public int countPage(TourguideDto tourguideDto) {
        return TourguideDao.countPage(tourguideDto);
    }

    /**
     * 申请导游
     */

    @Override
    public List<Tourguide> getTourguideByNameList1(TourguideDto tourguideDto) {
        return TourguideDao.getTourguideByNameList1(tourguideDto);
    }
    @Override
    public int countPage1(TourguideDto tourguideDto) {
        return TourguideDao.countPage1(tourguideDto);
    }

    @Override
    public int updateTourguide(Tourguide tourguide ) {
        return TourguideDao.updateTourguide(tourguide);
    }


    public int deleteTourguide(long id) {
        return TourguideDao.deleteTourguide(id);
    }

    public int addTourguide(Tourguide tourguide ) {
        return TourguideDao.addTourguide(tourguide);
    }



    public List<Tourguide>  getGuideNameList(Tourguide tourguide) {
        return TourguideDao.getGuideNameList(tourguide);
    }




}
