package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.InfoManagementDao;
import com.tourismmanagementbackend.dto.InfoManagementDto;
import com.tourismmanagementbackend.entity.InfoManagement;
import com.tourismmanagementbackend.service.InfoManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Infomanagement)表服务实现类
 *
 * @author makejava
 * @since 2024-07-14 16:37:25
 */
@Service("infomanagementService")
public class InfoManagementServiceImpl implements InfoManagementService {

    @Resource
    private InfoManagementDao infoManagementDao;

    @Override
    public List<InfoManagement> getInfoById(InfoManagementDto infoManagementDto) {
        return infoManagementDao.getInfoById(infoManagementDto);
    }

    @Override
    public int countPage(InfoManagementDto infoManagementDto) {
        return infoManagementDao.countPage(infoManagementDto);
    }

    @Override
    public int addInfo(InfoManagement infoManagement) {
        return infoManagementDao.addInfo(infoManagement);
    }

    @Override
    public int updateInfo(InfoManagement infoManagement) {
        return infoManagementDao.updateInfo(infoManagement);
    }

    @Override
    public int updateInfoStatus(List<Integer> ids) {
        return infoManagementDao.updatePublishStatus(ids);
    }

    @Override
    public List<InfoManagement> getInfoPublished(InfoManagementDto infoManagementDto) {
        return infoManagementDao.getInfoPublished(infoManagementDto);
    }

    @Override
    public int countPublished(InfoManagementDto infoManagementDto) {
        return infoManagementDao.countPublished(infoManagementDto);
    }
}
