package com.ifelseelif.blsslab1.Models.Domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class DbBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String header;
    private String briefInformation;
    private String mainText;
    private Date publishDate;
    @ManyToOne
    private DbCountry countryId;
}
