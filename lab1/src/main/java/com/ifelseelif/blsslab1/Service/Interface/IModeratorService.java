package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.DTO.StoryResponse;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;

import java.util.List;

public interface IModeratorService {
    List<DbReport> getAllReports();
    DbReport getReport(long id);
    void addCountry(String name);
    void addHotel(Hotel hotel);
    List<StoryResponse> getUnverifiedStories();
    String setVerifiedStory(long id);
    String publishMaterial(long id);
    void selectBestMaterial(long idOfMaterial);
}
