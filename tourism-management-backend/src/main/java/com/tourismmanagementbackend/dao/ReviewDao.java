package com.tourismmanagementbackend.dao;


import com.tourismmanagementbackend.dto.ReviewDto;
import com.tourismmanagementbackend.entity.Review;

import java.util.List;

/**
 * (Review)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-11 14:59:59
 */
public interface ReviewDao {

    List<Review> getReviewList(ReviewDto reviewDto);

    // 计算评论总数
    int countPage(ReviewDto reviewDto);

    // 删除指定评论
    int deleteReviewByReviewId(int id);

    // 根据用户名显示对应评论
    List<Review> getReviewByUsername(ReviewDto reviewDto);

    // 根据用户名计算评论总数
    int countPageByUsername(ReviewDto reviewDto);

    // 添加游客评论
    int addReview(Review review);

    // 找出所有的景区
    List<String> getAllScenic();

    // 找出所有的酒店
    List<String> getAllHotel();

    // 修改游客评论
    int updateReview(Review review);
}