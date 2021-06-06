package com.ifelseelif.blsslab1.models.dto;

import com.ifelseelif.blsslab1.models.domain.Blog;
import com.ifelseelif.blsslab1.models.domain.Review;
import com.ifelseelif.blsslab1.models.domain.Story;
import lombok.Data;

@Data
public class ReportResponse {
    private String text;
    private Blog blog;
    private Story story;
    private Review review;
}
