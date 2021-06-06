package com.ifelseelif.blsslab1.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class BlogDto {
    private String header;
    private String briefInformation;
    private String mainText;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date publishDate;
    private long countryId;
}
