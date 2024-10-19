package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.InfoManagement;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: InfoManagement
 * @Package com.tourismmanagementbackend.dto
 * @Description:
 * @date 2024/7/14 16:38
 */

@Data
public class InfoManagementDto extends InfoManagement implements Serializable {
    private int page;
    private int limit;
}
