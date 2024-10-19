package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.ReservationDto;
import com.tourismmanagementbackend.entity.Reservation;
import com.tourismmanagementbackend.service.ReservationService;
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
 * (Reservation)表控制层
 *
 * @author makejava
 * @since 2024-07-11 16:21:15
 */
@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Resource
    private ReservationService reservationService;

    /**
     * 根据旅客的姓名查询列表
     * @param
     * @return
     */
    @RequestMapping( "/getReservationByNameList")
    public DataResult getReservationByNameList(@RequestBody ReservationDto reservationDto){

        //计算分页参数
        reservationDto.setPage(PageUtils.getStartPage(reservationDto.getPage(),reservationDto.getLimit()));
        //获取学生数据
        List<Reservation> getReservationByNameList = reservationService.getReservationByNameList(reservationDto);
        //获取学生总数
        long  countPage = reservationService.countPage(reservationDto);
        System.out.println(getReservationByNameList);


        return DataResult.successByTotalData(getReservationByNameList,countPage);


    }

    /**
     * 得到预约人数的总数
     * @return
     */

    @RequestMapping( "/getReservationNum")
    public DataResult getReservationNum(){
        int  countPage_1 = reservationService.countPage_1();

        return DataResult.successByDatas(countPage_1);


    }





    @RequestMapping( "/deleteReservation")
    public DataResult deleteReservation(@RequestBody Reservation reservation){
        int i = reservationService.deleteReservation(reservation);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }

    }

    @RequestMapping( "/addVisitorInfo")
    public DataResult addVisitorInfo(@RequestBody Reservation reservation ){
        int i = reservationService.addVisitorInfo(reservation);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }

    }


    @RequestMapping( "/addReservation")
    public DataResult addReservation(@RequestBody Reservation reservation ){
        try {
            int i = reservationService.addReservation(reservation);

            if (i > 0) {
                return DataResult.succ();
            } else {
                return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
            }
        }catch (Exception e){
            return DataResult.errByErrCode(Code.ADD_RESERVATION_ERROR);
        }
    }


}

