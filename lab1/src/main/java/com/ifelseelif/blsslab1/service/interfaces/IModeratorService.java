package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.dto.Hotel;
import com.ifelseelif.blsslab1.models.dto.ReviewedReport;
import com.ifelseelif.blsslab1.models.dto.StoryResponse;
import com.ifelseelif.blsslab1.models.domain.DbMaterialRequest;
import com.ifelseelif.blsslab1.models.domain.DbReport;

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
