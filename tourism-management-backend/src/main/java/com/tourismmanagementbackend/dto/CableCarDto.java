package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Cablecar;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: CableCarDto
 * @Package com.tourismmanagementbackend.dto
 * @Description:
 * @date 2024/7/13 21:38
 */

@Data
public class CableCarDto extends Cablecar implements Serializable {
    private int page;
    private int limit;
}
