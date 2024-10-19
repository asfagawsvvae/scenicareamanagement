package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Complaint;
import lombok.Data;

import java.io.Serializable;

@Data
public class ComplaintDto extends Complaint implements Serializable {
    private int page;
    private int limit;
}
