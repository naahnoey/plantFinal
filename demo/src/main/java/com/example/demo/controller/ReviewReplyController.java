package com.example.demo.controller;

import java.time.LocalDate;
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

import com.example.demo.model.Review;
import com.example.demo.model.ReviewReply;
import com.example.demo.repository.ReviewReplyRepository;
import com.example.demo.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class ReviewReplyController {
	
	@Autowired
	private final ReviewReplyRepository reviewReplyRepository;
	
	@Autowired
	private final ReviewRepository reviewRepository;
	
	@PostMapping("/auth/reviewreply/add")
	public ResponseEntity<ReviewReply> createReply(@RequestBody ReviewReply reviewReply){
		try {
			ReviewReply newReply = this.reviewReplyRepository.save(new ReviewReply(reviewReply.getReview(), reviewReply.getRrcontent(), reviewReply.getUsername(), LocalDate.now(), null));
			return new ResponseEntity<>(newReply, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/auth/reviewreply/list/{rid}")
	public ResponseEntity<List<ReviewReply>> replyList(@RequestParam(required = false) Integer cid, @PathVariable("rid") Integer rid) {
		try {
			Optional<Review> reviewData = this.reviewRepository.findById(rid);
			Review review = reviewData.get();
			List<ReviewReply> reviewReply = this.reviewReplyRepository.findAllByReview(review);
			return new ResponseEntity<>(reviewReply, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/auth/reviewreply/modify/{rrid}")
	public ResponseEntity<ReviewReply> modifyReply(@PathVariable("rrid") Integer rrid, @RequestBody ReviewReply reviewReply) {
		try {
			Optional<ReviewReply> replyData = this.reviewReplyRepository.findById(rrid);
			if(replyData.isPresent()) {
				ReviewReply newReply = replyData.get();
				newReply.setRrcontent(reviewReply.getRrcontent());
				newReply.setRrmodifydate(LocalDate.now());
				return new ResponseEntity<>(this.reviewReplyRepository.save(newReply), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/auth/reviewreply/delete/{rrid}")
	public ResponseEntity<HttpStatus> replyList(@PathVariable("rrid") Integer rrid) {
		try {
			this.reviewReplyRepository.deleteById(rrid);
			return new ResponseEntity<>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
