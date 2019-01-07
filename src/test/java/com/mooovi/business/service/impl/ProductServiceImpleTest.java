package com.mooovi.business.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mooovi.business.entity.Product;
import com.mooovi.business.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImpleTest {
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository mockProductRepository;
	
	@Test
	public void testFindOne() {
		Product product = new Product();
		product.setId(1L);
		product.setTitle("テスト映画");
		when(mockProductRepository.findOne(1L)).thenReturn(product);
		
		assertThat(productService.findOne(1L).getTitle(), is("テスト映画"));
	}
	
	@Test
	public void testFindOneOrNew() {
		Product product = new Product();
		product.setId(1L);
		product.setTitle("テスト映画");
		when(mockProductRepository.findByTitle("テスト映画")).thenReturn(product);
		when(mockProductRepository.findByTitle("映画")).thenReturn(null);
		
		assertThat(productService.findOneOrNew("テスト映画").getId(), is(1L));
		assertThat(productService.findOneOrNew("映画").getId(), is(nullValue()));
	}

}
