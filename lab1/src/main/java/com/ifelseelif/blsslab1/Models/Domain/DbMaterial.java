package com.ifelseelif.blsslab1.Models.Domain;


import com.ifelseelif.blsslab1.Models.DTO.Status;
import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private TypeMaterial typeMaterial;
    private Status status;

    @OneToOne
    private DbBlog blog;

    @OneToOne
    private DbReview review;

    @OneToOne
    private DbStory story;
}
