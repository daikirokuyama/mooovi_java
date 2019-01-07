package com.mooovi.business.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mooovi.business.entity.Review;
import com.mooovi.business.repository.ReviewRepository;
import com.mooovi.business.service.ProductService;
import com.mooovi.business.service.ReviewService;
import com.mooovi.business.service.UserService;

@Service
@Transactional
public class ReviewServiceImple implements ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void save(Review review, Long productId, Long userId) {
		review.setProduct(productService.findOne(productId));
		review.setUser(userService.findOne(userId));
		reviewRepository.save(review);
	}
}
