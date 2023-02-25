package com.shoppingcart.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.payload.CategoryDto;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.repository.CategoryRepository;
import com.shoppingcart.repository.ProductRepository;
import com.shoppingcart.service.CategoryService;
import com.shoppingcart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	
	
	private CategoryService categoryService;
	
	
	
//	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
//		this.productRepository = productRepository;
//		this.categoryRepository = categoryRepository;
//	}

	

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			CategoryService categoryService) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
	}



	@Override
	public ProductDto createProduct(Long categoryId, ProductDto productDto) {
		// TODO Auto-generated method stub
		
		Product product = mapToEntity(productDto);
		
		Category category = categoryRepository.findById(categoryId)
											  .orElseThrow(
													  		() -> new ResourceNotFoundException("Category", "id", categoryId)
													  	  );
		product.setCategory(category);
		
		productRepository.save(product);
		
		return mapToDto(product);
	}
	
	
	@Override
	public List<ProductDto> getProductByCategoryId(Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Category", "id", categoryId)
						  	  );
		
		
		List<Product> listProducts = productRepository.findByCategoryId(category.getId());
		
		List <ProductDto> listProductsDto= listProducts.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
		
		return listProductsDto;
	}
	
	
	@Override
	public ProductDto getProductById(long productId) {
		
		Product product = productRepository.findById(productId).orElseThrow(
																				() -> new ResourceNotFoundException("Product", "id", productId)
																			);
		
		return mapToDto(product);
	}
	
	@Override
	public ProductDto updateProduct(long productId, ProductDto productDto) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
																				
		
		if( productDto.getCategoryId() != null) {
			CategoryDto newCategory = categoryService.getCategoryById(productDto.getCategoryId());					
			product.setCategory(mapToEntity(newCategory));
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
		}else {
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			
		}
																				
		
		
		productRepository.save(product);
			
		return mapToDto(product);
	}
	
	@Override
	public void deleteProduct(long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		productRepository.delete(product);
	}
	
	

	@Override
	public List<ProductDto> getAllProducts() {
		
		List<ProductDto> list= productRepository.findAll().stream().map(product -> mapToDto(product)).collect(Collectors.toList());
		
		
		return list;
	}

	
	
	//convert entity to Dto
		private ProductDto mapToDto(Product product) {
			
			ProductDto productDto = new ProductDto();
			
			productDto.setId(product.getId());
			productDto.setAdded_on(product.getAdded_on());
			
			
			productDto.setCategoryId(product.getCategory().getId());
			
			
			productDto.setPrice(product.getPrice());
			productDto.setName(product.getName());
			
			

			return productDto;
		}
			
			//convert Dto to entity
				private Product mapToEntity(ProductDto productDto) {
					Product product = new Product();
					
					product.setId(productDto.getId());
					product.setAdded_on(productDto.getAdded_on());
							
					
					product.setName(productDto.getName());
					product.setPrice(productDto.getPrice());


					return product;
				}
				
				
				//convert Dto to entity
				private Category mapToEntity(CategoryDto categoryDto) {
					Category category = new Category();
					
					category.setId(categoryDto.getId());
					
					category.setName(categoryDto.getName());
					
					return category;
				}





				
				
				
				
				
				
				
				



				



				



				
	
	
	

}
