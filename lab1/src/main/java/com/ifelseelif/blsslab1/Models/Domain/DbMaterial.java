package com.ifelseelif.blsslab1.Models.Domain;


import com.ifelseelif.blsslab1.Models.DTO.Status;
import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
import lombok.Data;

import javax.persistence.*;




@Entity
@Data
public class DbMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
