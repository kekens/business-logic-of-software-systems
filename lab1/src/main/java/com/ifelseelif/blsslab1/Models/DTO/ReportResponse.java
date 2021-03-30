package com.ifelseelif.blsslab1.Models.DTO;

import com.ifelseelif.blsslab1.Models.Domain.DbBlog;
import com.ifelseelif.blsslab1.Models.Domain.DbReview;
import com.ifelseelif.blsslab1.Models.Domain.DbStory;
import lombok.Data;

@Data
public class ReportResponse {
    private String text;
    private DbBlog blog;
    private DbStory story;
    private DbReview review;
}
