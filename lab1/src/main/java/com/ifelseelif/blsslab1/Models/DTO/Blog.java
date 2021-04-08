package com.ifelseelif.blsslab1.Models.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class Blog {
    private String header;
    private String briefInformation;
    private String mainText;
    private Date publishDate;
    private long countryId;
    private boolean isChecked;
}
