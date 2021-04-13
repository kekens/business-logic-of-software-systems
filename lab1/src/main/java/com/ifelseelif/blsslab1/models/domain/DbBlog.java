package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class DbBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;
    private String briefInformation;
    private String mainText;
    private Date publishDate;

    @ManyToOne
    private DbCountry country;

    public DbBlog(long id) {
        this.id = id;
    }
}
