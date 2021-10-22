package com.ifelseelif.touristapp.models.dto;

import com.ifelseelif.touristapp.models.domain.Blog;
import com.ifelseelif.touristapp.models.domain.Review;
import com.ifelseelif.touristapp.models.domain.Story;
import lombok.Data;

@Data
public class ReportResponse {
    private String text;
    private Blog blog;
    private Story story;
    private Review review;
}
