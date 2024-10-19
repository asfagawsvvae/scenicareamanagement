package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.CableCarDto;
import com.tourismmanagementbackend.entity.Cablecar;
import com.tourismmanagementbackend.service.CablecarService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (Cablecar)表控制层
 *
 * @author makejava
 * @since 2024-07-13 21:38:07
 */
@RestController
@RequestMapping("cablecar")
public class CableCarController {

    @Resource
    private CablecarService cablecarService;

    @RequestMapping("getCableCarByNameList")
    public DataResult getCableCarByNameList(@RequestBody CableCarDto cablecarDto){
        cablecarDto.setPage(PageUtils.getStartPage(cablecarDto.getPage(),cablecarDto.getLimit()));
        List<Cablecar> cablecarList = cablecarService.getCableCarByNameList(cablecarDto);
        long total = cablecarService.countPage(cablecarDto);
        return DataResult.successByTotalData(cablecarList,total);
    }

    @RequestMapping("addCableCar")
    public DataResult addCableCar(@RequestBody Cablecar cablecar){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        cablecar.setCableCarCode(uuid);
        int i=cablecarService.addCableCar(cablecar);
        if(i>0) {
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("deleteCableCar/{cableCarName}")
    public DataResult deleteCableCar(@PathVariable("cableCarName") String cableCarName){
        int i=cablecarService.deleteCableCar(cableCarName);
        if(i>0) {
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateCableCar")
    public DataResult updateCableCar(@RequestBody Cablecar cablecar){
        int i=cablecarService.updateCableCar(cablecar);
        if(i>0) {
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }

}

