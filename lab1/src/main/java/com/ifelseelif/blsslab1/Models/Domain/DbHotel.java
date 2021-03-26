package com.ifelseelif.blsslab1.Models.Domain;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DbHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private DbCountry countryId;
    private int rating;
}
