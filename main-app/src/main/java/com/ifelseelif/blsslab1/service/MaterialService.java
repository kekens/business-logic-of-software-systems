package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.*;
import com.ifelseelif.blsslab1.models.dto.*;
import com.ifelseelif.blsslab1.models.domain.*;
import com.ifelseelif.blsslab1.service.interfaces.IMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MaterialService implements IMaterialService {
    private Logger logger = LoggerFactory.getLogger(MaterialService.class);

    private final MaterialRepository materialRepository;
    private final StoryRepository storyRepository;
    private final BlogRepository blogRepository;
    private final ReviewRepository reviewRepository;
    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;
    private final MaterialRequestRepository materialRequestRepository;

    public MaterialService(MaterialRepository materialRepository, StoryRepository storyRepository,
                           BlogRepository blogRepository, ReviewRepository reviewRepository,
                           CountryRepository countryRepository, HotelRepository hotelRepository, MaterialRequestRepository materialRequestRepository) {
        this.materialRepository = materialRepository;
        this.storyRepository = storyRepository;
        this.blogRepository = blogRepository;
        this.reviewRepository = reviewRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.materialRequestRepository = materialRequestRepository;


    }

    @Override
    public List<Material> getAllMaterials() {
        return (List<Material>) materialRepository.findAll();
    }

    @Override
    public Material getMaterial(long id) {
        return materialRepository.getMaterialById(id);
    }

    @Override
    public void deleteMaterial(long id) {
        materialRepository.deleteById(id);
    }

    @Override
    public void createMaterial(TypeMaterial typeMaterial) {
        Material material = new Material();
        material.setTypeMaterial(typeMaterial);
        material.setStatus(Status.Draft);
        switch (typeMaterial) {
            case Blog:
                material.setBlog(new Blog(blogRepository.findLastBlog()));
                break;
            case Story:
                material.setStory(new Story(storyRepository.findLastStory()));
                break;
            case Review:
                material.setReview(new Review(reviewRepository.findLastReview()));
                break;
        }

        materialRepository.save(material);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setHeader(blogDto.getHeader());
        blog.setBriefInformation(blogDto.getBriefInformation());
        blog.setMainText(blogDto.getMainText());
        blog.setCountry(new Country(blogDto.getCountryId()));
        blog.setPublishDate(blogDto.getPublishDate());

        blogRepository.save(blog);

        this.createMaterial(TypeMaterial.Blog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setScoreOfLocation(reviewDto.getScoreOfLocation());
        review.setScoreOfService(reviewDto.getScoreOfService());
        review.setScoreOfStuff(reviewDto.getScoreOfStuff());
        review.setScoreOfFood(reviewDto.getScoreOfFood());
        review.setGoodHotel(reviewDto.getIsGoodHotel());
        review.setVisitedDate(reviewDto.getVisitedDate());
        review.setAdvantages(reviewDto.getAdvantages());
        review.setDisadvantages(reviewDto.getDisadvantages());
        review.setMainText(reviewDto.getMainText());
        review.setHotel(new Hotel(reviewDto.getHotelId()));

        reviewRepository.save(review);

        this.createMaterial(TypeMaterial.Review);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStory(StoryDto storyDto) {
        Story story = new Story();
        story.setHeader(storyDto.getHeader());
        story.setTravelDate(storyDto.getTravelDate());
        story.setBriefInformation(storyDto.getBriefInformation());
        story.setMainText(storyDto.getMainText());
        story.setVerified(false);

        Set<Country> countrySet = new HashSet<>();
        for (long countryId : storyDto.getCountries()) {
            countrySet.add(new Country(countryId));
        }

        story.setCountry(countrySet);
        storyRepository.save(story);
        this.createMaterial(TypeMaterial.Story);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlog(long id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Blog with id=" + id + " not found")
        ));

        Material material = materialRepository.findMaterialByBlog(blog);

        if (material.getStatus().equals(Status.Published)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Blog has been published");
        }

        Country country = countryRepository.findById(blogDto.getCountryId()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ("Country with id=" + id + " not found")
                ));
        blog.setHeader(blogDto.getHeader());
        blog.setPublishDate(blogDto.getPublishDate());
        blog.setMainText(blogDto.getMainText());
        blog.setCountry(country);

        blogRepository.save(blog);
        materialRequestRepository.changeRequestStatus(material.getId(), RequestStatus.Unchecked);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReview(long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Review with id=" + id + " not found")
        ));

        Material material = materialRepository.findMaterialByReview(review);

        if (material.getStatus().equals(Status.Published)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review has been published");
        }

        Hotel hotel = hotelRepository.findById(reviewDto.getHotelId()).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ("Hotel with id=" + reviewDto.getHotelId() + " not found")
                ));

        review.setAdvantages(reviewDto.getAdvantages());
        review.setDisadvantages(reviewDto.getDisadvantages());

        review.setVisitedDate(reviewDto.getVisitedDate());
        review.setGoodHotel(reviewDto.getIsGoodHotel());
        review.setMainText(reviewDto.getMainText());
        review.setHotel(hotel);

        review.setScoreOfService(reviewDto.getScoreOfService());
        review.setScoreOfFood(reviewDto.getScoreOfFood());
        review.setScoreOfStuff(reviewDto.getScoreOfStuff());
        review.setScoreOfLocation(reviewDto.getScoreOfLocation());

        reviewRepository.save(review);
        materialRequestRepository.changeRequestStatus(material.getId(), RequestStatus.Unchecked);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStory(long id, StoryDto storyDto) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Story with id=" + id + " not found")
        ));

        Material material = materialRepository.findMaterialByStory(story);

        if (material.getStatus().equals(Status.Published)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Story has been published");
        }

        Iterable<Country> dbCountries = countryRepository.findAllById(storyDto.getCountries());

        HashSet<Country> countries = new HashSet<>();
        dbCountries.iterator().forEachRemaining(countries::add);

        if (countries.size() != storyDto.getCountries().size()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ("Some country not found")
            );
        }

        story.setBriefInformation(storyDto.getBriefInformation());
        story.setMainText(storyDto.getMainText());
        story.setTravelDate(storyDto.getTravelDate());
        story.setHeader(storyDto.getHeader());
        story.setCountry(countries);

        storyRepository.save(story);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendMaterial(long id) {

        Optional<Material> dbMaterial = materialRepository.findById(id);

        if (dbMaterial.isPresent()) {
            if (!dbMaterial.get().getStatus().equals(Status.Draft)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            TypeMaterial typeMaterial = dbMaterial.get().getTypeMaterial();

            if (typeMaterial.equals(TypeMaterial.Story)) {
                materialRepository.changeStatus(id, Status.Published);
            } else {
                materialRepository.changeStatus(id, Status.Approving);
            }

            if (!dbMaterial.get().getTypeMaterial().equals(TypeMaterial.Story)) {

                MaterialRequest materialRequest = new MaterialRequest();

                materialRequest.setMaterial(dbMaterial.get());
                materialRequest.setRequestStatus(RequestStatus.Unchecked);

                materialRequestRepository.save(materialRequest);
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material not found");
        }

    }

    @Override
    public List<Material> getAllBestMaterials() {
        return null;
    }
}
