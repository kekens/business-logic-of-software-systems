package com.ifelseelif.blsslab1.Models.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
public class DbStory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String header;
    private Date travelDate;
    private String briefInformation;
    private String mainText;

    @ManyToMany
    private Set<DbCountry> country;
}
