package com.example.reviewsservice.service.impl;

import com.example.model.review.ReviewDTO;
import com.example.reviewsservice.entity.Review;
import com.example.reviewsservice.exception.ReviewNotFoundException;
import com.example.reviewsservice.mapper.ReviewMapper;
import com.example.reviewsservice.repository.ReviewRepository;
import com.example.reviewsservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Caching(
            put = {@CachePut(value = "review", key = "#result.id")},
            evict = {@CacheEvict(value = "listReview", key = "#reviewDTO.productId")}
    )
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = buildReview(reviewDTO);
        log.info("review: {}", review);
        Review createdReview = reviewRepository.save(review);
        return ReviewMapper.convertToDTO(createdReview);
    }

    @Override
    @Transactional
    @Caching(
            cacheable = {@Cacheable(value = "review", key = "#reviewDTO.id")},
            put = {@CachePut(value = "review", key = "#reviewDTO.id")},
            evict = {@CacheEvict(value = "listReview", key = "#reviewDTO.productId")}
    )
    public ReviewDTO editReview(ReviewDTO reviewDTO) {
        Review review = findById(reviewDTO.getId());
        review = ReviewMapper.convertToEntity(reviewDTO);
        log.info("review: {}", review);
        Review updatedReview = reviewRepository.saveAndFlush(review);
        return ReviewMapper.convertToDTO(updatedReview);
    }

    @Override
    @Transactional
    @Caching(
            evict = {@CacheEvict(value = "review", allEntries = true),
                    @CacheEvict(value = "listReview", allEntries = true)
            }
    )
    public void deleteById(long id) {
        Review review = findById(id);
        reviewRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "review", key = "#id")
    public Review findById(long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException());
        return review;
    }

    @Override
    public ReviewDTO getReviewById(long id) {
        Review review = findById(id);
        return ReviewMapper.convertToDTO(review);
    }

    @Override
    @Cacheable(value = "listReview", key = "#productId")
    public List<ReviewDTO> findReviewByProductId(long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        if (reviews.isEmpty()) throw new ReviewNotFoundException();
        return ReviewMapper.convertToDTOs(reviews);
    }

    private Review buildReview(ReviewDTO reviewDTO) {
        Review review = Review.builder().comment(reviewDTO.getComment())
                .rate(reviewDTO.getRate())
                .uid(reviewDTO.getUid())
                .productId(reviewDTO.getProductId())
                .build();
        return review;
    }
}
