package com.ifelseelif.blsslab1.Models.Domain;


import com.ifelseelif.blsslab1.Models.DTO.Status;
import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class DbMaterial {
    public DbMaterial(long id) {
        this.id = id;
    }

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
}
