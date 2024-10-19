package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Visitorinfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fengyang
 * @version V1.0
 * Copyright (c) 2024, fengyang@hwadee.com All Rights Reserved.
 * @ProjectName:tourism-management-backend
 * @Title: VisitorinfoDto
 * @Package com.tourismmanagementbackend.dto
 * @Description:
 * @date 2024/7/13 16:07
 */

@Data
public class VisitorinfoDto extends Visitorinfo implements Serializable{
    private int page;
    private int limit;
}
