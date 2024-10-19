package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.ReviewDao;
import com.tourismmanagementbackend.dto.ReviewDto;
import com.tourismmanagementbackend.entity.Review;
import com.tourismmanagementbackend.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Review)表服务实现类
 *
 * @author makejava
 * @since 2024-07-11 14:59:59
 */
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewDao reviewDao;

    @Override
    public List<Review> getReviewList(ReviewDto reviewDto) {
        return reviewDao.getReviewList(reviewDto);
    }

    @Override
    public int countPage(ReviewDto reviewDto) {
        return reviewDao.countPage(reviewDto);
    }

    @Override
    public int deleteReviewByReviewId(int id) {
        return reviewDao.deleteReviewByReviewId(id);
    }

    @Override
    public List<Review> getReviewByUsername(ReviewDto reviewDto) {
        return reviewDao.getReviewByUsername(reviewDto);
    }

    @Override
    public int countPageByUsername(ReviewDto reviewDto) {
        return reviewDao.countPageByUsername(reviewDto);
    }

    @Override
    public int addReview(Review review) {
        return reviewDao.addReview(review);
    }

    @Override
    public List<String> getAllScenic() {
        return reviewDao.getAllScenic();
    }

    @Override
    public List<String> getAllHotel() {
        return reviewDao.getAllHotel();
    }

    @Override
    public int updateReview(Review review) {
        return reviewDao.updateReview(review);
    }
}