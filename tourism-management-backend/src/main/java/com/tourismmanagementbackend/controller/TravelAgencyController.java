package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.TravelAgencyDto;
import com.tourismmanagementbackend.entity.TravelAgency;
import com.tourismmanagementbackend.service.TravelAgencyService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * (Travelagency)表控制层
 *
 * @author makejava
 * @since 2024-07-12 15:08:31
 */
@RestController
@RequestMapping("travelAgency")
public class TravelAgencyController {
    @Autowired
    private TravelAgencyService travelAgencyService;

    @RequestMapping("getTravelAgencyByNameList")
    public DataResult getTravelAgencyByNameList(@RequestBody TravelAgencyDto travelAgencyDto){
        travelAgencyDto.setPage(PageUtils.getStartPage(travelAgencyDto.getPage(),travelAgencyDto.getLimit()));
        List<TravelAgency> travelAgencyList = travelAgencyService.getTravelAgencyByNameList(travelAgencyDto);
        long countPage = travelAgencyService.countPage(travelAgencyDto);
        return DataResult.successByTotalData(travelAgencyList,countPage);
    }

    @RequestMapping("addTravelAgency")
    public DataResult addTravelAgency(@RequestBody TravelAgency travelAgency){
        String uuid = UUID.randomUUID().toString().replace("-","");
        travelAgency.setAgencyCode(uuid);
        int i = travelAgencyService.addTravelAgency(travelAgency);

        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("deleteTravelAgency/{travelAgencyName}")
    public DataResult deleteTravelAgency(@PathVariable String travelAgencyName){
        int i = travelAgencyService.deleteTravelAgency(travelAgencyName);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateTravelAgency")
    public DataResult updateTravelAgency(@RequestBody TravelAgency travelAgency){
        int i = travelAgencyService.updateTravelAgency(travelAgency);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }


    @RequestMapping("getAgencyNameList")
    public  DataResult getAgencyNameList(@RequestBody TravelAgency travelAgency){
        List<TravelAgency> getAgencyNameList = travelAgencyService.getAgencyNameList(travelAgency);

        return  DataResult.successByDataArray(getAgencyNameList);

    }

    @RequestMapping("getAgencyNameList_1")
    public  DataResult getAgencyNameList_1(){
        List<TravelAgency> getAgencyNameList = travelAgencyService.getAgencyNameList_1();

        return  DataResult.successByDataArray(getAgencyNameList);

    }





}

