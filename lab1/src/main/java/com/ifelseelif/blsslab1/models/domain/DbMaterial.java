package com.ifelseelif.blsslab1.models.domain;


import com.ifelseelif.blsslab1.models.dto.Status;
import com.ifelseelif.blsslab1.models.dto.TypeMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class DbMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TypeMaterial typeMaterial;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private DbBlog blog;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private DbReview review;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private DbStory story;

    private boolean isBest;

    public DbMaterial(long id) {
        this.id = id;
    }
}
