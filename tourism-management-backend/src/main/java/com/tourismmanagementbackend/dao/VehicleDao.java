package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.entity.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Vehicle)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-14 15:22:12
 */
public interface VehicleDao {
    List<Vehicle> getVehicleListByAgency(Vehicle vehicle);

    int addVehicle(Vehicle vehicle);

    int updateVehicle(Vehicle vehicle);

    int deleteVehicle(int vehicleId);
}

