package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.model.ReviewReply;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReviewReplyRepository;
import com.example.demo.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class ReviewController {
	
	@Autowired
	private final ReviewRepository reviewRepository;
	
	@Autowired
	private final ProductRepository productRepository;
	
	@Autowired
	private final ReviewReplyRepository reviewReplyRepository;
	
	@PostMapping("/auth/review/add")
	public ResponseEntity<Review> createReview(@RequestBody Review review){
		try {
			Review newReview = this.reviewRepository.save(new Review(review.getRtitle(), review.getRcontent(), review.getUsername(), review.getProduct(), LocalDate.now(), null));
			return new ResponseEntity<>(newReview, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/auth/review/list/{pid}")
	public ResponseEntity<List<Review>> reviewList(@RequestParam(required = false) Integer rid, @PathVariable("pid") Long pid){
		try {
			List<Review> review = new ArrayList<Review>();
			Optional<Product> productData = this.productRepository.findById(pid);
			Product product = productData.get();
			reviewRepository.findAllByProduct(product).forEach(review::add);
			return new ResponseEntity<>(review, HttpStatus.OK); 
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/auth/review/detail/{rid}")
	public ResponseEntity<Review> reviewbyId(@PathVariable("rid") Integer rid){
		try {
			Optional<Review> reviewData = reviewRepository.findById(rid);
			Review review = reviewData.get();
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/auth/review/delete/{rid}")
	public ResponseEntity<HttpStatus> delreviewbyId(@PathVariable("rid") Integer rid){
		try {
			Optional<Review> reviewData = reviewRepository.findById(rid);
			Review review = reviewData.get();
			List<ReviewReply> reviewReply = reviewReplyRepository.findAllByReview(review);
			reviewReplyRepository.deleteAll(reviewReply);
			reviewRepository.deleteById(rid);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/auth/review/modify/{rid}")
	public ResponseEntity<Review> reviewModify(@PathVariable("rid") Integer rid, @RequestBody Review review){
		Optional<Review> reviewData = reviewRepository.findById(rid);
		if(reviewData.isPresent()) {
			Review newReview = reviewData.get();
			newReview.setRtitle(review.getRtitle());
			newReview.setRcontent(review.getRcontent());
			newReview.setRmodifydate(LocalDate.now());
			return new ResponseEntity<>(reviewRepository.save(newReview), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/auth/review/list")
	public ResponseEntity<List<Review>> reviewAllList(@RequestParam(required = false) Integer rid){
		try {
		List<Review> allReview = reviewRepository.findAll();
			return new ResponseEntity<>(allReview, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
