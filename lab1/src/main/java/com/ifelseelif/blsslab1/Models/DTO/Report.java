package com.ifelseelif.blsslab1.Models.DTO;

import com.ifelseelif.blsslab1.Models.Domain.DbMaterial;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Report {
    private long materialId;
    private String text;
}
