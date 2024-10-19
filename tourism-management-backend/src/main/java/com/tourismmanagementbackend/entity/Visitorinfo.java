package com.tourismmanagementbackend.entity;

import java.time.LocalDate;
import java.util.Date;
import java.io.Serializable;

/**
 * 游客信息表，全(Visitorinfo)实体类
 *
 * @author makejava
 * @since 2024-07-13 16:03:27
 */
public class Visitorinfo implements Serializable {
    private static final long serialVersionUID = -11846963662197551L;

    private Integer userId;

    private String username;

    private String phone;

    private String idCard;

    private Integer isDrive;

    private String destination;

    private String hotel;

    private String guide;

    private LocalDate date;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getIsDrive() {
        return isDrive;
    }

    public void setIsDrive(Integer isDrive) {
        this.isDrive = isDrive;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

