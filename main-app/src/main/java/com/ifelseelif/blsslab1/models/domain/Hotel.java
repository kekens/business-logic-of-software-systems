package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;

    private int rating;

    public Hotel(long id) {
        this.id = id;
    }
}
