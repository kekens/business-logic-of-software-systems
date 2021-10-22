package com.ifelseelif.touristapp.service.interfaces;

import com.ifelseelif.touristapp.models.dto.*;
import com.ifelseelif.touristapp.models.domain.MaterialRequest;
import com.ifelseelif.touristapp.models.domain.Report;

import java.util.List;

public interface IModeratorService {
    List<Report> getAllReports();
    Report getReport(long id);
    void addCountry(String name);
    void addHotel(HotelDto hotelDto);
    List<StoryResponse> getUnverifiedStories();
    void setVerifiedStory(long id);
    void rejectMaterial(ModeratorRejectRequest moderatorRejectRequest);
    void publishMaterial(long id);
    void selectBestMaterial(long idOfMaterial);
    List<MaterialRequest> getAllMaterialRequests();
    void closeReport(ReviewedReport reviewedReport);

}
