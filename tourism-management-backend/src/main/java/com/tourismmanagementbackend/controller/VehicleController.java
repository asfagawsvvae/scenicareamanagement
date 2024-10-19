package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.entity.Vehicle;
import com.tourismmanagementbackend.service.VehicleService;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Vehicle)表控制层
 *
 * @author makejava
 * @since 2024-07-14 15:22:12
 */
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Resource
    private VehicleService vehicleService;

    @RequestMapping("getVehicleListByAgency")
    public DataResult getVehicleListByAgency(@RequestBody Vehicle vehicle){
        return DataResult.successByDataArray(vehicleService.getVehicleListByAgency(vehicle));
    }

    @RequestMapping("addVehicle")
    public DataResult addVehicle(@RequestBody Vehicle vehicle){
        int i = vehicleService.addVehicle(vehicle);
        if (i > 0) {
            return DataResult.succ();
        } else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("updateVehicle")
    public DataResult updateVehicle(@RequestBody Vehicle vehicle){
        int i = vehicleService.updateVehicle(vehicle);
        if (i > 0) {
            return DataResult.succ();
        } else {
            return DataResult.errByErrCode(Code.UPDATE_STUDENT_ERROR);
        }
    }

    @RequestMapping("deleteVehicle/{vehicleId}")
    public DataResult deleteVehicle(@PathVariable("vehicleId") int vehicleId){
        int i = vehicleService.deleteVehicle(vehicleId);
        if (i > 0) {
            return DataResult.succ();
        } else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }
}

