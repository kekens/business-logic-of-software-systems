package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.database.*;
import com.ifelseelif.touristapp.models.dto.*;
import com.ifelseelif.touristapp.models.domain.*;
import com.ifelseelif.touristapp.security.CustomUserDetails;
import com.ifelseelif.touristapp.service.interfaces.IMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MaterialService implements IMaterialService {

    private final MaterialRepository materialRepository;
    private final StoryRepository storyRepository;
    private final BlogRepository blogRepository;
    private final ReviewRepository reviewRepository;
    private final CountryRepository countryRepository;
    private final HotelRepository hotelRepository;
    private final MaterialRequestRepository materialRequestRepository;
    private final UserRepository userRepository;

    public MaterialService(MaterialRepository materialRepository, StoryRepository storyRepository,
                           BlogRepository blogRepository, ReviewRepository reviewRepository, CountryRepository countryRepository,
                           HotelRepository hotelRepository, MaterialRequestRepository materialRequestRepository,
                           UserRepository userRepository) {
        this.materialRepository = materialRepository;
        this.storyRepository = storyRepository;
        this.blogRepository = blogRepository;
        this.reviewRepository = reviewRepository;
        this.countryRepository = countryRepository;
        this.hotelRepository = hotelRepository;
        this.materialRequestRepository = materialRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Material> getAllMaterials() {
        CustomUserDetails principal = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (principal.getAuthorities().toString().contains("MODERATOR")) {
            return (List<Material>) materialRepository.findAll();
        } else {
            return materialRepository.findAllByUser(userRepository.findByUsername(principal.getUsername()));
        }
    }

    @Override
    public Material getMaterial(long id) {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent() && !material.get().getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect material ID");
        }

        return materialRepository.getMaterialById(id);
    }

    @Override
    public void deleteMaterial(long id) {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        Optional<Material> material = materialRepository.findById(id);

        if (material.isPresent() && !material.get().getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect material ID");
        }

        materialRepository.deleteById(id);
    }

    @Override
    public void createMaterial(TypeMaterial typeMaterial) {
        Material material = new Material();
        material.setTypeMaterial(typeMaterial);
        material.setStatus(Status.DRAFT);
        switch (typeMaterial) {
            case BLOG:
                material.setBlog(new Blog(blogRepository.findLastBlog()));
                break;
            case STORY:
                material.setStory(new Story(storyRepository.findLastStory()));
                break;
            case REVIEW:
                material.setReview(new Review(reviewRepository.findLastReview()));
                break;
        }

        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        material.setUser(userRepository.findByUsername(username));

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

        this.createMaterial(TypeMaterial.BLOG);
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

        this.createMaterial(TypeMaterial.REVIEW);
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
        this.createMaterial(TypeMaterial.STORY);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateBlog(long id, BlogDto blogDto) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Blog with id=" + id + " not found")
        ));

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        Material material = materialRepository.findMaterialByBlog(blog);

        if (!material.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect blog ID");
        }

        if (!material.getStatus().equals(Status.DRAFT)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review has been sent");
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
        materialRequestRepository.changeRequestStatus(material.getId(), RequestStatus.UNCHECKED);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReview(long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Review with id=" + id + " not found")
        ));

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        Material material = materialRepository.findMaterialByReview(review);

        if (!material.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect review ID");
        }

        if (!material.getStatus().equals(Status.DRAFT)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review has been sent");
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
        materialRequestRepository.changeRequestStatus(material.getId(), RequestStatus.UNCHECKED);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStory(long id, StoryDto storyDto) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, ("Story with id=" + id + " not found")
        ));

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        Material material = materialRepository.findMaterialByStory(story);

        if (!material.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect story ID");
        }

        if (!material.getStatus().equals(Status.DRAFT)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review has been sent");
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

        Optional<Material> material = materialRepository.findById(id);

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername());

        if (material.isPresent()) {

            if (!material.get().getUser().equals(user)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect material ID");
            }

            if (!material.get().getStatus().equals(Status.DRAFT)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect status of material");
            }

            TypeMaterial typeMaterial = material.get().getTypeMaterial();

            if (typeMaterial.equals(TypeMaterial.STORY)) {
                materialRepository.changeStatus(id, Status.PUBLISHED);
            } else {
                materialRepository.changeStatus(id, Status.APPROVING);
            }

            if (!material.get().getTypeMaterial().equals(TypeMaterial.STORY)) {

                MaterialRequest materialRequest = new MaterialRequest();

                materialRequest.setMaterial(material.get());
                materialRequest.setRequestStatus(RequestStatus.UNCHECKED);

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
