package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Material material;

    private String text;
}
