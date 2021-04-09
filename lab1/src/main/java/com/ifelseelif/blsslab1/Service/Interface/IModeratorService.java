package com.ifelseelif.blsslab1.Service.Interface;

import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.DTO.ReviewedReport;
import com.ifelseelif.blsslab1.Models.DTO.StoryResponse;
import com.ifelseelif.blsslab1.Models.Domain.DbMaterialRequest;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;

import java.util.List;

public interface IModeratorService {
    List<DbReport> getAllReports();
    DbReport getReport(long id);
    void addCountry(String name);
    void addHotel(Hotel hotel);
    List<StoryResponse> getUnverifiedStories();
    void setVerifiedStory(long id);
    void rejectMaterial(long id);
    void publishMaterial(long id);
    void selectBestMaterial(long idOfMaterial);
    List<DbMaterialRequest> getAllMaterialRequests();
    void closeReport(ReviewedReport reviewedReport);

}
