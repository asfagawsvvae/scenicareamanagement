package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.ReviewDto;
import com.tourismmanagementbackend.entity.Review;
import com.tourismmanagementbackend.service.ReviewService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Review)表控制层
 *
 * @author makejava
 * @since 2024-07-11 14:59:59
 */
@RestController
@RequestMapping("review")
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    // 展现评论区的所有内容/根据景点搜索相关评论
    @RequestMapping("/getReviewList")
    public DataResult getReviewList(@RequestBody ReviewDto reviewDto){
        // 计算分页存储
        reviewDto.setPage(PageUtils.getStartPage(reviewDto.getPage(), reviewDto.getLimit()));
        // 获取评论列表
        List<Review> allReview = reviewService.getReviewList(reviewDto);
        // 获取评论总数
        long countPage = reviewService.countPage(reviewDto);
        return DataResult.successByTotalData(allReview, countPage);
    }

    // 删除评论
    @RequestMapping("/deleteReviewByReviewId/{id}")
    public DataResult deleteReviewByReviewId(@PathVariable("id") int id){
        int i = reviewService.deleteReviewByReviewId(id);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.DELETE_REVIEW_ERROR);
        }
    }

    // 根据用户名显示对应评论
    @RequestMapping("/getReviewByUsername")
    public DataResult getReviewByUsername(@RequestBody ReviewDto reviewDto){
        // 计算分页存储
        reviewDto.setPage(PageUtils.getStartPage(reviewDto.getPage(), reviewDto.getLimit()));
        // 获取评论列表
        List<Review> allReview = reviewService.getReviewByUsername(reviewDto);
        // 获取评论总数
        long countPageByUsername = reviewService.countPageByUsername(reviewDto);
        return DataResult.successByTotalData(allReview, countPageByUsername);
    }

    // 添加游客评论
    @RequestMapping("/addReview")
    public DataResult addReview(@RequestBody Review review){
        int i = reviewService.addReview(review);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.ADD_REVIEW_ERROR);
        }
    }

    // 找出所有的景区
    @RequestMapping("/getAllScenic")
    public DataResult getAllScenic(){
        List<String> allScenic = reviewService.getAllScenic();
        return DataResult.successByDataArray(allScenic);
    }

    // 找出所有的酒店
    @RequestMapping("/getAllHotel")
    public DataResult getAllHotel(){
        List<String> allHotel = reviewService.getAllHotel();
        return DataResult.successByDataArray(allHotel);
    }

    // 修改游客评论
    @RequestMapping("/updateReview")
    public DataResult updateReview(@RequestBody Review review){
        int i = reviewService.updateReview(review);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.UPDATE_REVIEW_ERROR);
        }
    }
}