package com.ifelseelif.touristapp.models.domain;


import com.ifelseelif.touristapp.models.dto.Status;
import com.ifelseelif.touristapp.models.dto.TypeMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TypeMaterial typeMaterial;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Blog blog;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Review review;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Story story;

    private boolean isBest;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Material(long id) {
        this.id = id;
    }
}
