package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.CableCarDto;
import com.tourismmanagementbackend.entity.Cablecar;
import com.tourismmanagementbackend.dao.CableCarDao;
import com.tourismmanagementbackend.service.CablecarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Cablecar)表服务实现类
 *
 * @author makejava
 * @since 2024-07-13 21:38:07
 */
@Service("cablecarService")
public class CablecarServiceImpl implements CablecarService {

    @Resource
    private CableCarDao cablecarDao;
    @Override
    public List<Cablecar> getCableCarByNameList(CableCarDto cablecarDto) {
        return cablecarDao.getCableCarByNameList(cablecarDto);
    }

    @Override
    public int countPage(CableCarDto cablecarDto) {
        return cablecarDao.countPage(cablecarDto);
    }

    @Override
    public int addCableCar(Cablecar cablecar) {
        return cablecarDao.addCableCar(cablecar);
    }

    @Override
    public int deleteCableCar(String cableCarName) {
        return cablecarDao.deleteCableCar(cableCarName);
    }

    @Override
    public int updateCableCar(Cablecar cablecar) {
        return cablecarDao.updateCableCar(cablecar);
    }
}
