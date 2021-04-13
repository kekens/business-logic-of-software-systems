package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class DbHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private DbCountry country;

    private int rating;

    public DbHotel(long id) {
        this.id = id;
    }
}
