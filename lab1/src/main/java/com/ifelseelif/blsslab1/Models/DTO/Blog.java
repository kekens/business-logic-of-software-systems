package com.ifelseelif.blsslab1.Models.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class Blog {
    @NotNull(message = "header cannot be null")
    private String header;

    @NotNull(message = "brief information cannot be null")
    private String briefInformation;

    @NotNull(message = "main text cannot be null")
    private String mainText;

    @NotNull(message = "publish date cannot be null")
    private Date publishDate;
    private long countryId;
    private boolean isChecked;
}
