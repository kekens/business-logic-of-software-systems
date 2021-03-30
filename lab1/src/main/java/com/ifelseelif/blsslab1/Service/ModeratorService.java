package com.ifelseelif.blsslab1.Service;

import com.ifelseelif.blsslab1.Database.CountryRepository;
import com.ifelseelif.blsslab1.Database.HotelRepository;
import com.ifelseelif.blsslab1.Database.ReportRepository;
import com.ifelseelif.blsslab1.Models.DTO.Hotel;
import com.ifelseelif.blsslab1.Models.Domain.DbCountry;
import com.ifelseelif.blsslab1.Models.Domain.DbHotel;
import com.ifelseelif.blsslab1.Models.Domain.DbReport;
import com.ifelseelif.blsslab1.Service.Interface.IModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorService implements IModeratorService {

    private final ReportRepository reportRepository;
    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public ModeratorService(ReportRepository reportRepository, CountryRepository countryRepository,
                            HotelRepository hotelRepository) {
        this.reportRepository = reportRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<DbReport> getAllReports() {
        return (List<DbReport>) reportRepository.findAll();
    }

    @Override
    public void addCountry(String name) {
        DbCountry dbCountry = new DbCountry();
        dbCountry.setName(name);
        this.countryRepository.save(dbCountry);
    }

    @Override
    public void addHotel(Hotel hotel) {
        DbHotel dbHotel = new DbHotel();
        dbHotel.setName(hotel.getName());
        dbHotel.setCountry(new DbCountry(hotel.getCountryId()));
        dbHotel.setRating(hotel.getRating());

        hotelRepository.save(dbHotel);
    }
}
