package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.entity.Vehicle;
import com.tourismmanagementbackend.dao.VehicleDao;
import com.tourismmanagementbackend.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Vehicle)表服务实现类
 *
 * @author makejava
 * @since 2024-07-14 15:22:12
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleDao vehicleDao;

    @Override
    public List<Vehicle> getVehicleListByAgency(Vehicle vehicle) {
        return vehicleDao.getVehicleListByAgency(vehicle);
    }

    @Override
    public int addVehicle(Vehicle vehicle) {
        return vehicleDao.addVehicle(vehicle);
    }

    @Override
    public int updateVehicle(Vehicle vehicle) {
        return vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public int deleteVehicle(int vehicleId) {
        return vehicleDao.deleteVehicle(vehicleId);
    }
}
