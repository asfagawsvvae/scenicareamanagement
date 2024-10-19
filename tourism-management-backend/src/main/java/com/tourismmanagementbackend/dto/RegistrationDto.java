package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Registration;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationDto extends Registration implements Serializable {
    private int page;
    private int limit;
}
