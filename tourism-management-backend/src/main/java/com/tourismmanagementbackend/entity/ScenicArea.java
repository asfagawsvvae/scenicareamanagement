package com.tourismmanagementbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Scenicarea)实体类
 *
 * @author makejava
 * @since 2024-07-11 23:47:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScenicArea implements Serializable {
    private static final long serialVersionUID = 475731947314985816L;

    private String scenicAreaName;

    private Integer maxCapacity;

    private Integer parkingSpaces;

    private String address;

    private String imageUrl;

    private Double ticketPrice;

    private Integer visitorCount;

    private String date;

    private String scenicAreaCode;

    private String description;

    private Integer international;

    private Integer occupiedCapacity;



}

