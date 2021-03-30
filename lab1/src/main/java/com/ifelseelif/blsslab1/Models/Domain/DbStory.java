package com.ifelseelif.blsslab1.Models.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class DbStory {

    public DbStory(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;
    private Date travelDate;
    private String briefInformation;
    private String mainText;

    @ManyToMany
    private Set<DbCountry> country;
}
