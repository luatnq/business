package com.example.reviewsservice.controller;

import com.example.model.review.ReviewDTO;
import com.example.reviewsservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("Create review {}", reviewDTO);
        ReviewDTO createdReview = reviewService.createReview(reviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("Update review: {}", reviewDTO);
        ReviewDTO updatedReview = reviewService.editReview(reviewDTO);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteReview(@PathVariable long id) {
        log.info("Delete review with id = {}", id);
        reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getReviewById(@PathVariable long id) {
        log.info("Search review by id = {}", id);
        ReviewDTO review = reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getReviewByProductId(@RequestParam long productId) {
        log.info("Search review by productId = {}", productId);
        List<ReviewDTO> listReview = reviewService.findReviewByProductId(productId);
        return new ResponseEntity<>(listReview, HttpStatus.OK);
    }
}
