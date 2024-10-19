package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.InfoManagementDto;
import com.tourismmanagementbackend.entity.InfoManagement;

import java.util.List;

/**
 * (Infomanagement)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-14 16:37:25
 */
public interface InfoManagementDao {

    List<InfoManagement> getInfoById(InfoManagementDto infoManagementDto );

    int countPage(InfoManagementDto infoManagementDto);

    int addInfo(InfoManagement infoManagement);

    int updateInfo(InfoManagement infoManagement);

    int updatePublishStatus(List<Integer> infoManagementList);

    List<InfoManagement> getInfoPublished(InfoManagementDto infoManagementDto);
    int countPublished(InfoManagementDto infoManagementDto);

}

