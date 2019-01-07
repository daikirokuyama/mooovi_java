package com.mooovi.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mooovi.business.entity.Product;

public interface ProductService {
	
	Product save(Product product);
	
	Product findOneOrNew(String title);
	
	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findTop5();
	
	Product findOne(Long id);
	
	List<Product> findAllByTitleLike(String keyword);
	
}
