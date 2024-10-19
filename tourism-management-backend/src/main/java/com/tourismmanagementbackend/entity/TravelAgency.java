package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TravelAgency)实体类
 *
 * @author makejava
 * @since 2024-07-12 15:08:32
 */

@Data
public class TravelAgency implements Serializable {
    private static final long serialVersionUID = -57211044619121684L;

    private Integer agencyId;

    private String agencyCode;

    private String agencyName;

    private Integer maxTourists;

    private Double rating;

    private Integer international;

    private String date;

    private Integer touristCount;
}

