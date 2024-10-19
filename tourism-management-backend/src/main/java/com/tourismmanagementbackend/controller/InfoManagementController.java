package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.InfoManagementDto;
import com.tourismmanagementbackend.dto.InfoUpdateDto;
import com.tourismmanagementbackend.entity.InfoManagement;
import com.tourismmanagementbackend.service.InfoManagementService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.List;

/**
 * (Infomanagement)表控制层
 *
 * @author makejava
 * @since 2024-07-14 16:37:25
 */
@RestController
@RequestMapping("infoManagement")
public class InfoManagementController {

    @Resource
    private InfoManagementService infoManagementService;

    @RequestMapping("getInfoById")
    public DataResult getInfoById(@RequestBody InfoManagementDto infoManagementDto){
        infoManagementDto.setPage(PageUtils.getStartPage(infoManagementDto.getPage(),infoManagementDto.getLimit()));
        List<InfoManagement> getInfoById = infoManagementService.getInfoById(infoManagementDto);
        long count = infoManagementService.countPage(infoManagementDto);
        return DataResult.successByTotalData(getInfoById,count);
    }

    @RequestMapping("addInfo")
    public DataResult addInfo(@RequestBody InfoManagement infoManagement){
        int i=infoManagementService.addInfo(infoManagement);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateInfo")
    public DataResult updateInfo(@RequestBody InfoManagement infoManagement){
        int i=infoManagementService.updateInfo(infoManagement);
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }

    @RequestMapping("updatePublishStatus")
    public DataResult updatePublishStatus(@RequestBody InfoUpdateDto ids){
        int i=infoManagementService.updateInfoStatus(ids.getIds());
        if(i>0){
            return DataResult.succ();
        }else {
            return DataResult.err();
        }
    }

    @RequestMapping("getInfoPublished")
    public DataResult getInfoPublished(@RequestBody InfoManagementDto infoManagementDto){
        infoManagementDto.setPage(PageUtils.getStartPage(infoManagementDto.getPage(),infoManagementDto.getLimit()));
        List<InfoManagement> getInfoPublished = infoManagementService.getInfoPublished(infoManagementDto);
        long count = infoManagementService.countPublished(infoManagementDto);
        return DataResult.successByTotalData(getInfoPublished,count);
    }

}

