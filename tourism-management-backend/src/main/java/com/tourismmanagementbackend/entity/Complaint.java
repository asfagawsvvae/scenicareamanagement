package com.tourismmanagementbackend.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 投诉(Complaint)实体类
 *
 * @author makejava
 * @since 2024-07-13 15:12:05
 *
 */

@Data
public class Complaint implements Serializable {
    private static final long serialVersionUID = -67126487671786283L;

    private Integer complaintId;

    private String username;

    private String targetType;

    private String targetName;

    private String content;

}

