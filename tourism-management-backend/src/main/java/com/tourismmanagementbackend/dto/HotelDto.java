package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Hotel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: HotelDto
 * @Package com.tourismmanagementbackend.dto
 * @Description:
 * @date 2024/7/11 21:14
 */

@Data
public class HotelDto extends Hotel implements Serializable {
    private int page;
    private int limit;
}
