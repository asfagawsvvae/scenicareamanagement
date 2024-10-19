package com.tourismmanagementbackend.dto;

import com.tourismmanagementbackend.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto extends Reservation implements Serializable {

    private  int page;
    private  int limit ;
}
