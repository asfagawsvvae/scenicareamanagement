package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.dao.TravelAgencyDao;
import com.tourismmanagementbackend.entity.TravelAgency;
import lombok.Data;

import java.io.Serializable;

@Data
public class TravelAgencyDto extends TravelAgency implements Serializable  {
    private int page;
    private int limit;
}
