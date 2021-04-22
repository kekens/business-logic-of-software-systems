package com.ifelseelif.blsslab1.models.dto;

import com.ifelseelif.blsslab1.models.domain.DbBlog;
import com.ifelseelif.blsslab1.models.domain.DbReview;
import com.ifelseelif.blsslab1.models.domain.DbStory;
import lombok.Data;

@Data
public class ReportResponse {
    private String text;
    private DbBlog blog;
    private DbStory story;
    private DbReview review;
}
