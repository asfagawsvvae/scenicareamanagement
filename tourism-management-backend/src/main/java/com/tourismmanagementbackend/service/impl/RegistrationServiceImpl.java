package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.RegistrationDto;
import com.tourismmanagementbackend.entity.Registration;
import com.tourismmanagementbackend.dao.RegistrationDao;
import com.tourismmanagementbackend.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Registration)表服务实现类
 *
 * @author makejava
 * @since 2024-07-15 15:40:28
 */
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationDao registrationDao;

    @Override
    public List<Registration> getRegistrationList(RegistrationDto registrationDto) {
        return registrationDao.getRegistrationList(registrationDto);
    }

    @Override
    public int countPage(RegistrationDto registrationDto) {
        return registrationDao.countPage(registrationDto);
    }

    @Override
    public int addRegistration(Registration registration) {
        return registrationDao.addRegistration(registration);
    }

    @Override
    public int checkReservation(Registration registration) {
        return registrationDao.checkReservation(registration);
    }

    @Override
    public int updateVisitorCount(String scenicAreaName) {
        return registrationDao.updateVisitorCount(scenicAreaName);
    }

    @Override
    public int updateOccupiedCapacity(String scenicAreaName) {
        return registrationDao.updateOccupiedCapacity(scenicAreaName);
    }
}
