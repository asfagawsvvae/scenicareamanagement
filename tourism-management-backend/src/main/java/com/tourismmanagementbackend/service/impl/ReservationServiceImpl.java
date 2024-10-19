package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.entity.Reservation;
import com.tourismmanagementbackend.dao.ReservationDao;
import com.tourismmanagementbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Reservation)表服务实现类
 *
 * @author makejava
 * @since 2024-07-11 16:21:15
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private  ReservationDao reservationDao;

    @Override
    public List<Reservation> getReservationByNameList(ReservationDto reservationDto) {
        return reservationDao.getReservationByNameList(reservationDto);
    }
    @Override
    public int countPage(ReservationDto reservationDto) {
        return reservationDao.countPage( reservationDto);
    }
    public int countPage_1() {
        return reservationDao.countPage_1();
    }



    @Override
    public int deleteReservation(Reservation reservation) {return reservationDao.deleteReservation(reservation);
    }

    @Override
    public int addVisitorInfo(Reservation reservation) {
        return reservationDao.addVisitorInfo(reservation);
    }


    public int addReservation(Reservation reservation) {return reservationDao.addReservation(reservation);}

}
