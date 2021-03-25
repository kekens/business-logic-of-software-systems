package com.ifelseelif.blsslab1.Models.DTO;

import java.time.LocalDateTime;

public class Material {
    private String Header;
    private String BriefInformation;
    private String MainText;
    private LocalDateTime PublishDate;

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getBriefInformation() {
        return BriefInformation;
    }

    public void setBriefInformation(String briefInformation) {
        BriefInformation = briefInformation;
    }

    public String getMainText() {
        return MainText;
    }

    public void setMainText(String mainText) {
        MainText = mainText;
    }

    public LocalDateTime getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        PublishDate = publishDate;
    }
}
