package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.ReviewDto;
import com.tourismmanagementbackend.entity.Review;

import java.util.List;

/**
 * (Review)表服务接口
 *
 * @author makejava
 * @since 2024-07-11 14:59:59
 */
public interface ReviewService {

    List<Review> getReviewList(ReviewDto reviewDto);

    int countPage(ReviewDto reviewDto);

    int deleteReviewByReviewId(int id);

    List<Review> getReviewByUsername(ReviewDto reviewDto);

    int countPageByUsername(ReviewDto reviewDto);

    int addReview(Review review);

    List<String> getAllScenic();

    List<String> getAllHotel();

    int updateReview(Review review);
}