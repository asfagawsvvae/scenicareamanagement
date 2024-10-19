package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.InfoManagementDto;
import com.tourismmanagementbackend.entity.InfoManagement;

import java.util.List;

/**
 * (Infomanagement)表服务接口
 *
 * @author makejava
 * @since 2024-07-14 16:37:25
 */
public interface InfoManagementService {

    List<InfoManagement>  getInfoById(InfoManagementDto infoManagementDto);

    int countPage(InfoManagementDto infoManagementDto);

    int addInfo(InfoManagement infoManagement);

    int updateInfo(InfoManagement infoManagement);

    int updateInfoStatus( List<Integer> ids);

    List<InfoManagement> getInfoPublished(InfoManagementDto infoManagementDto);

    int countPublished(InfoManagementDto infoManagementDto);

}
