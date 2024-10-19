package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.TourguideDto;
import com.tourismmanagementbackend.entity.Tourguide;
import com.tourismmanagementbackend.service.TourguideService;
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
 * (Tourguide)表控制层
 *
 * @author makejava
 * @since 2024-07-11 19:43:38
 */
@RestController
@RequestMapping("tourguide")
public class TourguideController {
    @Resource
    private TourguideService tourguideService;

    /**
     * 根据学生的姓名查询列表o
     * @param tourguideDto
     * @return
     */


    @RequestMapping( "getTourguideByNameList")
    public DataResult getStudentByNameList(@RequestBody TourguideDto tourguideDto){

        //计算分页参数
        tourguideDto.setPage(PageUtils.getStartPage(tourguideDto.getPage(),tourguideDto.getLimit()));
        //获取学生数据
        List<Tourguide> tourguideByNameList = tourguideService.getTourguideByNameList(tourguideDto);
        //获取学生总数
        long  countPage = tourguideService.countPage(tourguideDto);
        return DataResult.successByTotalData(tourguideByNameList,countPage);
    }



    @RequestMapping( "/getTourguideByNameList1")
    public DataResult getStudentByNameList1(@RequestBody TourguideDto tourguideDto){
        //计算分页参数
        tourguideDto.setPage(PageUtils.getStartPage(tourguideDto.getPage(),tourguideDto.getLimit()));
        //获取学生数据
        List<Tourguide> tourguideByNameList = tourguideService.getTourguideByNameList1(tourguideDto);
        //获取学生总数
        long  countPage1 = tourguideService.countPage1(tourguideDto);
        return DataResult.successByTotalData(tourguideByNameList,countPage1);
    }



    @RequestMapping( "/updateTourguide")
    public DataResult updateSTourguide(@RequestBody Tourguide tourguide ){
        int i = tourguideService.updateTourguide(tourguide);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }

    }

    @RequestMapping( "/deleteTourguide/{guideId}")
    public DataResult deleteTourguide(@PathVariable("guideId") long guideId ){
        int i = tourguideService.deleteTourguide(guideId);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }

    }

    @RequestMapping( "/addTourguide")
    public DataResult addTourguide(@RequestBody Tourguide tourguide ){
        int i = tourguideService.addTourguide(tourguide);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }

    }

    /**
     * 由旅行社得到导游的姓名
     * @param tourguide
     * @return
     */
    @RequestMapping("/getGuideNameList")
    public  DataResult getGuideNameList(@RequestBody Tourguide tourguide){
        System.out.println(tourguide);

        List<Tourguide> getGuideNameList = tourguideService.getGuideNameList(tourguide);
        System.out.println("aaaaaaaaaaaaaaaaaaa"+getGuideNameList);
        return  DataResult.successByDataArray(getGuideNameList);

    }





}

