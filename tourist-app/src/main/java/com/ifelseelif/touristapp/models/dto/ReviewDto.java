package com.ifelseelif.touristapp.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ReviewDto {
    private int scoreOfLocation;
    private int scoreOfService;
    private int scoreOfStuff;
    private int scoreOfFood;
    @JsonProperty
    private boolean isGoodHotel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date visitedDate;
    private String advantages;
    private String disadvantages;
    private String mainText;
    private long hotelId;

    public int getScoreOfLocation() {
        return scoreOfLocation;
    }

    public int getScoreOfService() {
        return scoreOfService;
    }

    public int getScoreOfStuff() {
        return scoreOfStuff;
    }

    public int getScoreOfFood() {
        return scoreOfFood;
    }

    public boolean getIsGoodHotel() {
        return isGoodHotel;
    }

    public Date getVisitedDate() {
        return visitedDate;
    }

    public String getAdvantages() {
        return advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public String getMainText() {
        return mainText;
    }

    public long getHotelId() {
        return hotelId;
    }
}
