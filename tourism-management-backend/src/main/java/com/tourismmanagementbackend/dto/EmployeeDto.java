package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Employee;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto extends Employee implements Serializable {
    private int page;                   // 分页
    private int limit;                  // 每页评论条数
}