package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.dto.TourguideDto;
import com.tourismmanagementbackend.entity.Reservation;
import com.tourismmanagementbackend.entity.Tourguide;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Tourguide)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-11 19:43:38
 */
public interface TourguideDao {

    /**
     * 导游在职人员
     * @param TourguideDto
     * @return
     */
    List<Tourguide> getTourguideByNameList(TourguideDto TourguideDto);

    int  countPage(TourguideDto tourguideDto);
    /**
     * 申请导游人员
     */
    List<Tourguide> getTourguideByNameList1(TourguideDto TourguideDto);

    int  countPage1(TourguideDto tourguideDto);
//录用
    int updateTourguide(Tourguide tourguide );
//不录用
    int deleteTourguide(long id);


    int addTourguide(Tourguide tourguide );


    List<Tourguide>  getGuideNameList(Tourguide tourguide );


}

