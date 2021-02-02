package com.example.reviewsservice.service;

import com.example.model.review.ReviewDTO;
import com.example.reviewsservice.entity.Review;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO editReview(ReviewDTO reviewDTO);

    void deleteById(long id);

    Review findById(long id);

    ReviewDTO getReviewById(long id);

    List<ReviewDTO> findReviewByProductId(long productId);
}
