package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DbReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private DbMaterial material;

    private String text;
}
