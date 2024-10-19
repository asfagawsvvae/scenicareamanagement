package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.ScenicArea;
import lombok.Data;

import java.io.Serializable;
@Data

public class ScenicAreaDto extends ScenicArea implements Serializable {

    private  int page;
    private  int limit ;

}
