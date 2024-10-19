package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Review;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewDto extends Review implements Serializable {
    private int page;                   // 分页
    private int limit;                  // 每页评论条数
}