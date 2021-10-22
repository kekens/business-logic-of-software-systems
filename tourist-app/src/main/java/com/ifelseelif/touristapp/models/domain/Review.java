package com.ifelseelif.touristapp.models.domain;

import  lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int scoreOfLocation;
    private int scoreOfService;
    private int scoreOfStuff;
    private int scoreOfFood;
    private boolean isGoodHotel;
    private Date visitedDate;
    private String advantages;
    private String disadvantages;
    private String mainText;

    @ManyToOne
    private Hotel hotel;

    public Review(long id) {
        this.id = id;
    }
}
