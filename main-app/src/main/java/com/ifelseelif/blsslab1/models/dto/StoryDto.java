package com.ifelseelif.blsslab1.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class StoryDto {
    private String header;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date travelDate;
    private String briefInformation;
    private String mainText;
    private Set<Long> countries;
}
