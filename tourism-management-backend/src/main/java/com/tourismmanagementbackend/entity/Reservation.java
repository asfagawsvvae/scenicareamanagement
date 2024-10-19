package com.tourismmanagementbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.io.Serializable;

/**
 * (Reservation)实体类
 *
 * @author makejava
 * @since 2024-07-14 19:42:55
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation implements Serializable {
    private static final long serialVersionUID = -26980749234049826L;

    private String username;

    private String phone;

    private String scenicarea;

    private String hotel;

    private String travelagency;

    private String date;

    private Integer parking;

    private String guide;



}

