package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.dto.TourguideDto;
import com.tourismmanagementbackend.entity.Reservation;
import com.tourismmanagementbackend.entity.Tourguide;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Tourguide)表服务接口
 *
 * @author makejava
 * @since 2024-07-11 19:43:38
 */
public interface TourguideService {
    /**
     * 在职导游
     * @param TourguideDto
     * @return
     */
    List<Tourguide> getTourguideByNameList(TourguideDto TourguideDto);

    int countPage(TourguideDto TourguideDto);

    /**
     * 申请导游
     * @param TourguideDto
     * @return
     */

    List<Tourguide> getTourguideByNameList1(TourguideDto TourguideDto);

    int countPage1(TourguideDto TourguideDto);

    int updateTourguide(Tourguide tourguide);

    int deleteTourguide(long id );

    int addTourguide(Tourguide tourguide);

    List<Tourguide>  getGuideNameList(Tourguide tourguide );




}
