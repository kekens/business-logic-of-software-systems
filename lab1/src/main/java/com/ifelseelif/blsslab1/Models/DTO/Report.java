package com.ifelseelif.blsslab1.Models.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Report {
    @NotNull
    private long materialId;
    @NotNull
    private String text;
}
