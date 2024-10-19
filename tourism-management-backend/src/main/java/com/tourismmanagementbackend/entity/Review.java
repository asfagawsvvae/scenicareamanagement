package com.tourismmanagementbackend.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * (Review)实体类
 *
 * @author makejava
 * @since 2024-07-11 19:03:07
 */
public class Review implements Serializable {
    private static final long serialVersionUID = -36043947799785559L;

    private Integer reviewId;

    private String username;

    private Double rating;

    private String comment;

    private String target;

    private LocalDate reviewDate;


    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}