package com.ifelseelif.blsslab1.models.domain;

import com.ifelseelif.blsslab1.models.dto.RequestStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DbMaterialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private DbMaterial dbMaterial;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

}
