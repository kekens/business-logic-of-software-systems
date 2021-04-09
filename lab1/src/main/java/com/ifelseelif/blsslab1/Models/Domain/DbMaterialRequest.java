package com.ifelseelif.blsslab1.Models.Domain;

import com.ifelseelif.blsslab1.Models.DTO.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
