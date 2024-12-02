package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a new review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }

    // Get all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAllReviews();
    }

    // Get a review by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.findReviewById(id);
        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a review by ID
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Optional<Review> updated = reviewService.updateReview(id, updatedReview);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a review by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
