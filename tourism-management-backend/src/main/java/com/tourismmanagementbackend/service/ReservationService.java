package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dao.ReservationDao;
import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Reservation)表服务接口
 *
 * @author makejava
 * @since 2024-07-11 16:21:15
 */
public interface ReservationService {

    List<Reservation> getReservationByNameList(ReservationDto reservationDto);


    int countPage(ReservationDto reservationDto);
    int countPage_1();

    int deleteReservation(Reservation reservation);


    int addVisitorInfo(Reservation reservation);


    int addReservation(Reservation reservation);

}
