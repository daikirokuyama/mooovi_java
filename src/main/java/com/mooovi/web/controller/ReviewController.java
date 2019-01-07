package com.mooovi.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mooovi.business.entity.Product;
import com.mooovi.business.entity.Review;
import com.mooovi.business.service.ProductService;
import com.mooovi.business.service.ReviewService;
import com.mooovi.security.LoginUserDetails;
import com.mooovi.web.form.ReviewForm;

@Controller
public class ReviewController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/products/{productId}/reviews")
	public String newReview(@PathVariable Long productId, ReviewForm form, Model model) {
		Product product = productService.findOne(productId);
		model.addAttribute("product", product);
		return "reviews/new";
	}
	
	@PostMapping("/products/{productId}/reviews")
	public String createReview(@PathVariable Long productId, @Validated ReviewForm form, BindingResult result, Model model, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if (result.hasErrors()) {
			return newReview(productId, form, model);
		}
		Review review = new Review();
		BeanUtils.copyProperties(form, review);
		reviewService.save(review, productId, loginUserDetails.getUserId());
		return "redirect:/";
	}

}
