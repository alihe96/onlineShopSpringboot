package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Transactional
    public Optional<Review> updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setProduct(updatedReview.getProduct());
            existingReview.setUser(updatedReview.getUser());
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            return reviewRepository.save(existingReview);
        });
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
