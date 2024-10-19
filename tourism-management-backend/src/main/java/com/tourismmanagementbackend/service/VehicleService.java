package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Vehicle)表服务接口
 *
 * @author makejava
 * @since 2024-07-14 15:22:12
 */
public interface VehicleService {

    List<Vehicle> getVehicleListByAgency(Vehicle vehicle);

    int addVehicle(Vehicle vehicle);

    int updateVehicle(Vehicle vehicle);

    int deleteVehicle(int vehicleId);

}
