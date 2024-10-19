package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Reservation;
import com.tourismmanagementbackend.entity.Tourguide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Lirenfan
 * @version V1.0
 * @ProjectName:tourism-management-backend
 * @Title: TourguideDto
 * @Package com.tourismmanagementbackend.dto
 * @Description:导游
 * @date 2024/7/11 20:07
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TourguideDto extends Tourguide implements Serializable {

    private  int page;
    private  int limit ;
}