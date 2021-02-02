package com.example.reviewsservice.repository;

import com.example.reviewsservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUid(String uid);

    List<Review> findAllByProductId(long productId);
}
