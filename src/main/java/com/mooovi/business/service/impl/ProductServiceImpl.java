package com.mooovi.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mooovi.business.entity.Product;
import com.mooovi.business.repository.ProductRepository;
import com.mooovi.business.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product findOneOrNew(String title) {
		Product product = productRepository.findByTitle(title);
		if (product == null) product = new Product();
		return product;
	}
	
	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Override
	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}
	
	@Override
	public List<Product> findAllByTitleLike(String keyword) {
		return productRepository.findAllByTitleLike("%" + keyword + "%");
	}
	
	@Override
	public Page<Product> findTop5() {
		return productRepository.findTop(new PageRequest(0, 5));
	}
}
