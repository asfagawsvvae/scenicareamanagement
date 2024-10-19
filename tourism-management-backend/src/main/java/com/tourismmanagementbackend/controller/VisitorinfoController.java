package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.VisitorinfoDto;
import com.tourismmanagementbackend.entity.Visitorinfo;
import com.tourismmanagementbackend.service.VisitorinfoService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 游客信息表，全(Visitorinfo)表控制层
 *
 * @author makejava
 * @since 2024-07-13 16:03:27
 */
@RestController
@RequestMapping("visitorinfo")
public class VisitorinfoController {

    @Resource
    private VisitorinfoService visitorinfoService;

    @RequestMapping("getVisitorinfoByVisitorNameList")
    public DataResult getVisitorinfoByVisitorNameList(@RequestBody VisitorinfoDto visitorinfoDto){
        visitorinfoDto.setPage(PageUtils.getStartPage(visitorinfoDto.getPage(),visitorinfoDto.getLimit()));
        List<Visitorinfo> visitorinfoList = visitorinfoService.getVisitorinfoByVisitorNameList(visitorinfoDto);
        long countPage = visitorinfoService.countPage(visitorinfoDto);
        return DataResult.successByTotalData(visitorinfoList,countPage);
    }

    @RequestMapping("deleteVisitorinfo/{username}")
    public DataResult deleteVisitorinfo(@PathVariable("username") String username){
        int i = visitorinfoService.deleteVisitorinfo(username);
        if(i>0){
            return DataResult.succ();
        }else{
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }



    @RequestMapping("getVisitorParkingNum")
    public DataResult getVisitorParkingNum(@RequestBody Visitorinfo visitorinfo){
        int visitorParkingNum = visitorinfoService.getVisitorParkingNum(visitorinfo);

        return DataResult.successByDatas(visitorParkingNum);

    }

    /**
     * 通过酒店名字得到酒店的车位数量
     * @param visitorinfo
     * @return
     */
    @RequestMapping("getVisitorHotelParkingNum")
    public DataResult getVisitorHotelParkingNum(@RequestBody Visitorinfo visitorinfo){
        int VisitorHotelParkingNum = visitorinfoService.getVisitorHotelParkingNum(visitorinfo);

        return DataResult.successByDatas(VisitorHotelParkingNum);

    }
}

