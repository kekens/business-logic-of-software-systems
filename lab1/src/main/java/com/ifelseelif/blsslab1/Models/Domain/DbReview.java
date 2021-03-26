package com.ifelseelif.blsslab1.Models.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class DbReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
    private DbHotel hotel;
}
