package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.ScenicAreaDto;
import com.tourismmanagementbackend.entity.ScenicArea;
import com.tourismmanagementbackend.service.ScenicAreaService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (Scenicarea)表控制层
 *
 * @author makejava
 * @since 2024-07-11 15:28:54
 */
@RestController
@RequestMapping("scenicArea")
public class ScenicAreaController {
    @Resource
    private ScenicAreaService scenicAreaService;
    @RequestMapping("getScenicAreaList")
    public DataResult getScenicAreaList(@RequestBody ScenicAreaDto scenicAreaDto){
        scenicAreaDto.setPage(PageUtils.getStartPage(scenicAreaDto.getPage(),scenicAreaDto.getLimit()));
        List<ScenicArea> test = scenicAreaService.getScenicAreaList(scenicAreaDto);
        long countPage = scenicAreaService.countPage(scenicAreaDto);
        return DataResult.successByTotalData(test,countPage);
    }

    @RequestMapping("deleteScenicArea/{scenicAreaName}")
    public DataResult deleteScenicArea(@PathVariable("scenicAreaName") String scenicAreaName){
        int i = scenicAreaService.deleteScenicArea(scenicAreaName);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }

    @RequestMapping("addScenicArea")
    public DataResult addScenicArea(@RequestBody ScenicArea scenicArea){
        String uuid = UUID.randomUUID().toString().replace("-","");
        scenicArea.setScenicAreaCode(uuid);
        int i = scenicAreaService.addScenicArea(scenicArea);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateScenicArea")
    public DataResult updateScenicArea(@RequestBody ScenicArea scenicArea){
        int i = scenicAreaService.updateScenicArea(scenicArea);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }

    @RequestMapping("getScenicAreaListByName/{scenicAreaName}")
    public DataResult getScenicAreaListByName(@PathVariable("scenicAreaName") String scenicAreaName){
        return DataResult.successByDataArray(scenicAreaService.getScenicAreaListByName(scenicAreaName));
    }
    @RequestMapping("getScenicInfo")
    public DataResult getScenicInfoList(){
        return DataResult.successByDataArray(scenicAreaService.getScenicInfo());
    }
    @RequestMapping("getScenicInfo_1")
    public DataResult getScenicInfoList_1(){
        return DataResult.successByDataArray(scenicAreaService.getScenicInfo_1());
    }


    @RequestMapping("getRateByName/{scenicAreaName}")
    public DataResult getRateByName(@PathVariable("scenicAreaName") String scenicAreaName){
        return DataResult.successByDatas(scenicAreaService.getRateByName(scenicAreaName));
    }
}

