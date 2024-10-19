package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.entity.Reservation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Reservation)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-11 16:21:15
 */
public interface ReservationDao {

    List<Reservation> getReservationByNameList(ReservationDto reservationDto);

    int  countPage(ReservationDto reservationDto);

    int  countPage_1();//查询总页数


    int deleteReservation(Reservation reservation);

    int addVisitorInfo(Reservation reservation);

    int addReservation(Reservation reservation);






}

