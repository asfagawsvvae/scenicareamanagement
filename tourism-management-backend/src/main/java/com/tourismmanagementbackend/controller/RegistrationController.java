package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.RegistrationDto;
import com.tourismmanagementbackend.entity.Registration;
import com.tourismmanagementbackend.entity.ScenicArea;
import com.tourismmanagementbackend.service.RegistrationService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Registration)表控制层
 *
 * @author makejava
 * @since 2024-07-15 15:40:28
 */
@RestController
@RequestMapping("registration")
public class RegistrationController {
    @Resource
    private RegistrationService registrationService;

    @RequestMapping("getRegistrationList")
    public DataResult getRegistrationList(@RequestBody RegistrationDto registrationDto){
        registrationDto.setPage(PageUtils.getStartPage(registrationDto.getPage(),registrationDto.getLimit()));
        List<Registration> registrationList = registrationService.getRegistrationList(registrationDto);
        long countPage = registrationService.countPage(registrationDto);
        return DataResult.successByTotalData(registrationList,countPage);
    }

    @RequestMapping("addRegistration")
    public DataResult addRegistration(@RequestBody Registration registration){
        int i = registrationService.checkReservation(registration);
        System.out.println(i);
        if (i > 0){
            int j = registrationService.addRegistration(registration);
            if (j > 0){
                registrationService.updateVisitorCount(registration.getDestination());
                if (registration.getParking() == 1){
                    registrationService.updateOccupiedCapacity(registration.getDestination());
                }
                return DataResult.succ();
            }else {
                return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
            }
        }else {
            return DataResult.errByErrCode(Code.ADD_REGISTRATION_ERROR);
        }
    }
}

