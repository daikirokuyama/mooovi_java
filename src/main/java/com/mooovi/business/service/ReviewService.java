package com.mooovi.business.service;

//import org.springframework.data.domain.Page;

//import com.mooovi.business.entity.Product;
import com.mooovi.business.entity.Review;

public interface ReviewService {
	
	void save(Review review, Long productId, Long userId);
	
}
