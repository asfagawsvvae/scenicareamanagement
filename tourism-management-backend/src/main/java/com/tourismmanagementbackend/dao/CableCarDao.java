package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.CableCarDto;
import com.tourismmanagementbackend.entity.Cablecar;

import java.util.List;

/**
 * (Cablecar)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-13 21:38:07
 */
public interface CableCarDao {

    List<Cablecar> getCableCarByNameList(CableCarDto cablecarDto);

    int countPage(CableCarDto cablecarDto);

    int addCableCar(Cablecar cablecar);

    int deleteCableCar(String cableCarName);

    int updateCableCar(Cablecar cablecar);

}

