package com.ifelseelif.responseservice.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;
    private Date travelDate;
    private String briefInformation;
    private String mainText;

    @ManyToMany
    private Set<Country> country;

    private boolean isVerified;

    public Story(long id) {
        this.id = id;
    }
}
