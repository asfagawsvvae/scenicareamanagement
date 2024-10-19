package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.RegistrationDto;
import com.tourismmanagementbackend.entity.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Registration)表服务接口
 *
 * @author makejava
 * @since 2024-07-15 15:40:28
 */
public interface RegistrationService {
    List<Registration> getRegistrationList(RegistrationDto registrationDto);

    int countPage(RegistrationDto registrationDto);

    int addRegistration(Registration registration);

    int checkReservation(Registration registration);

    int updateVisitorCount(String scenicAreaName);

    int updateOccupiedCapacity(String scenicAreaName);
}
