package com.ifelseelif.blsslab1.Models.DTO;

import lombok.Data;

@Data
public class Report {
    @NotNull
    private long materialId;
    @NotNull
    private String text;
}
