package com.ifelseelif.blsslab1.models.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}