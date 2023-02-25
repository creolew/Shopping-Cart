package com.shoppingcart.service;


import java.util.List;

import com.shoppingcart.payload.ProductDto;


public interface ProductService {
	
	ProductDto createProduct(Long categoryId, ProductDto productDto);

	List<ProductDto> getProductByCategoryId(Long categoryId);
	
	ProductDto getProductById(long productId);
	
	ProductDto updateProduct(long productId, ProductDto productDto);
	
	void deleteProduct(long productId);
	
	List<ProductDto> getAllProducts();
}
