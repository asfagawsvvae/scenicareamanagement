package com.tourismmanagementbackend.entity;

import lombok.ToString;

import java.io.Serializable;

/**
 * (Tourguide)实体类
 *
 * @author makejava
 * @since 2024-07-11 19:43:38
 */
@ToString
public class Tourguide implements Serializable {
    private static final long serialVersionUID = 416418710941305201L;

    private Integer guideid;

    private String guidename;

    private String gender;

    private Integer age;

    private String agencyname;

    private Double rating;

    private String employmentstatus;


    public Integer getGuideid() {
        return guideid;
    }

    public void setGuideid(Integer guideid) {
        this.guideid = guideid;
    }

    public String getGuidename() {
        return guidename;
    }

    public void setGuidename(String guidename) {
        this.guidename = guidename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getEmploymentstatus() {
        return employmentstatus;
    }

    public void setEmploymentstatus(String employmentstatus) {
        this.employmentstatus = employmentstatus;
    }

}

