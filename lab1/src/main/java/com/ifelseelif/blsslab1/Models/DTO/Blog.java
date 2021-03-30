package com.ifelseelif.blsslab1.Models.DTO;

import com.ifelseelif.blsslab1.Models.Domain.DbCountry;
import lombok.Data;

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
