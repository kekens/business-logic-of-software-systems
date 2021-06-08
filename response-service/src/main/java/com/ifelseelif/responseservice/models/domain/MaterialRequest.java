package com.ifelseelif.responseservice.models.domain;

import com.ifelseelif.responseservice.models.dto.RequestStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MaterialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Material material;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

}
