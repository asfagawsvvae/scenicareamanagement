package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.ScenicAreaDao;
import com.tourismmanagementbackend.dto.ScenicAreaDto;
import com.tourismmanagementbackend.entity.ScenicArea;

import com.tourismmanagementbackend.service.ScenicAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Scenicarea)表服务实现类
 *
 * @author makejava
 * @since 2024-07-11 23:47:55
 */
@Service("scenicAreaService")
public class ScenicAreaServiceImpl implements ScenicAreaService {
    @Resource
    private ScenicAreaDao scenicAreaDao;


    @Override
    public List<ScenicArea> getScenicAreaList(ScenicAreaDto scenicAreaDto) {
        return scenicAreaDao.getScenicAreaList(scenicAreaDto);
    }

    @Override
    public int deleteScenicArea(String scenicAreaName) {
        return scenicAreaDao.deleteScenicArea(scenicAreaName);
    }

    @Override
    public int addScenicArea(ScenicArea scenicArea) {
        return scenicAreaDao.addScenicArea(scenicArea);
    }

    @Override
    public int updateScenicArea(ScenicArea scenicArea) {
        return scenicAreaDao.updateScenicArea(scenicArea);
    }

    @Override
    public List<ScenicArea> getScenicAreaListByName(String scenicAreaName) {
        return scenicAreaDao.getScenicAreaListByName(scenicAreaName);
    }

    @Override
    public int countPage(ScenicAreaDto scenicAreaDto) {
        return scenicAreaDao.countPage(scenicAreaDto);
    }
    @Override
    public List<ScenicArea> getScenicInfo(){return scenicAreaDao.getScenicInfo(); };
    @Override
    public List<ScenicArea> getScenicInfo_1(){return scenicAreaDao.getScenicInfo_1(); };
    @Override
    public double getRateByName(String scenicAreaName) {
        return scenicAreaDao.getRateByName(scenicAreaName);
    }
}
