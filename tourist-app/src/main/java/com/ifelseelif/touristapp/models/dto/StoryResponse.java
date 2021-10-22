package com.ifelseelif.touristapp.models.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class StoryResponse {
    private long id;
    private String header;
    private Date travelDate;
    private String briefInformation;
    private String mainText;
    private Set<String> countries;
}
