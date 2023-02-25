package com.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.model.Product;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}



	@PostMapping("/{categoryId}/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductDto> createProduct(@PathVariable(name = "categoryId") long categoryId,
													@RequestBody ProductDto productDto
			
												   ){
		
		ProductDto createdProduct= productService.createProduct(categoryId, productDto);
		
		return new ResponseEntity<ProductDto>(createdProduct, HttpStatus.CREATED);
				
				
	}

	@GetMapping("/{categoryId}/getProductByCategoryId")
	public List<ProductDto> getAllProductsByCategoryId(@PathVariable(name = "categoryId") long categoryId){
		
		List<ProductDto> listProductsDto= productService.getProductByCategoryId(categoryId);
		
		return listProductsDto;
	}
	
	@GetMapping("/getAll")
	public List<ProductDto> getAllProducts(){
		return (productService.getAllProducts());
	}
	
	
	
	
	@GetMapping("/{productId}/getProductByProductId")
	public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "productId") long productId){
		
		ProductDto product = productService.getProductById(productId);
		
		return  ResponseEntity.ok(product);
	}
	
	@PutMapping("/{productId}/updateProduct")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "productId") long productId, @RequestBody ProductDto productDto){
		
		
		return new ResponseEntity<ProductDto>(productService.updateProduct(productId, productDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}/deleteProduct")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") long productId){
		
		productService.deleteProduct(productId);
		
		return new ResponseEntity("Category with id: " + productId + " was deleted successfully", HttpStatus.OK);
	}


}
