package com.ifelseelif.notificationservice.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;
    private String briefInformation;
    private String mainText;
    private Date publishDate;

    @ManyToOne
    private Country country;

    public Blog(long id) {
        this.id = id;
    }
}
