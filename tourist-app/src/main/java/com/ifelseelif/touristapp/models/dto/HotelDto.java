package com.ifelseelif.touristapp.models.dto;

import lombok.Data;

@Data
public class HotelDto {
    private String name;
    private int rating;
    private long countryId;
}
