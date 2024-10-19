package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.RegistrationDto;
import com.tourismmanagementbackend.entity.Registration;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Registration)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-15 15:40:28
 */
public interface RegistrationDao {
    List<Registration> getRegistrationList(RegistrationDto registrationDto);

    int countPage(RegistrationDto registrationDto);

    int addRegistration(Registration registration);

    int checkReservation(Registration registration);

    int updateVisitorCount(String scenicAreaName);

    int updateOccupiedCapacity(String scenicAreaName);

}

