package com.example.reviewsservice.mapper;

import com.example.model.review.ReviewDTO;
import com.example.reviewsservice.entity.Review;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ReviewDTO convertToDTO(Review review) {
        final ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }
    public static List<ReviewDTO> convertToDTOs(List<Review> reviews){
        return reviews.stream().map(ReviewMapper::convertToDTO).collect(Collectors.toList());
    }

    public static Review convertToEntity(ReviewDTO reviewDTO) {
        final Review review = modelMapper.map(reviewDTO, Review.class);
        return review;
    }
}
