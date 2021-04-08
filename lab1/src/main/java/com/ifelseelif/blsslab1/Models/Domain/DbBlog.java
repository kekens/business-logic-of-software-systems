package com.ifelseelif.blsslab1.Models.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class DbBlog {

    public DbBlog(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;
    private String briefInformation;
    private String mainText;
    private Date publishDate;
    @ManyToOne
    private DbCountry country;
    private boolean isChecked;
}
