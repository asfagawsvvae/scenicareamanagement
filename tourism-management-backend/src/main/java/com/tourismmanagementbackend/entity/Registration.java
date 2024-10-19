package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Registration)实体类
 *
 * @author makejava
 * @since 2024-07-15 15:40:28
 */

@Data
public class Registration implements Serializable {
    private static final long serialVersionUID = 441013158682060130L;

    private String username;

    private String destination;

    private Integer parking;

    private String date;

}

