package com.ifelseelif.blsslab1.Models.Domain;

import com.ifelseelif.blsslab1.Models.DTO.TypeMaterial;
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
