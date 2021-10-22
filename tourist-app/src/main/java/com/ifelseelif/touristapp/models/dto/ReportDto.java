package com.ifelseelif.touristapp.models.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReportDto {
    @NotNull
    private long materialId;
    @NotNull
    private String text;
}
