package com.shoppingcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.payload.LineItemDto;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.payload.ProductDtoInCart;
import com.shoppingcart.repository.LineItemRepository;
import com.shoppingcart.service.LineItemService;

@RestController
@RequestMapping("/lineItem")
public class LineItemController {
	
	LineItemService lineItemService;

	public LineItemController(LineItemService lineItemService) {
		this.lineItemService = lineItemService;
	}
	
	@PostMapping("/{cartId}/{productId}/createLineItem")
	public ResponseEntity<LineItemDto> createLineItem(@PathVariable(name = "cartId") long cartId,
													  @PathVariable(name = "productId") long productId,
													  @RequestBody LineItemDto lineItemDto){
			
		LineItemDto createdLineItem = lineItemService.createLineItem(lineItemDto, cartId, productId);
		
		return new ResponseEntity<LineItemDto>(createdLineItem, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{cartId}/getAllProductsByCartId")
	public List<ProductDtoInCart> getAllProductsInLineItemByCartId(@PathVariable (name = "cartId") Long cartId){
			
		
		return lineItemService.getAllProductsWithCartId(cartId);
	}
	
	@GetMapping("/{userId}/getAllProductsByUserId")
	public List<ProductDtoInCart> getAllProductsInLineItemByUserId(@PathVariable (name = "userId") Long userId){
			
		
		return lineItemService.getAllProductsWithUserIdInLineItem(userId);
	}
	
	
	
	
	
	@PutMapping("/{cartId}/{productId}/updateQuantity")
	public ResponseEntity<LineItemDto> updateQuantityByCartIdAndProductId(@PathVariable(name = "cartId") long cartId, 
														  @PathVariable(name = "productId") long productId,
														  @RequestBody LineItemDto lineItemDto) {
	
		LineItemDto lineItem = lineItemService.updateQuantityInLineItemByCartIdAndProductId(cartId, productId, lineItemDto);
	
		return new ResponseEntity<LineItemDto>(lineItem, HttpStatus.OK);

	
	}
	
	@PutMapping("/{lineItemId}/{productId}/updateProductInLineItem")
	public ResponseEntity<LineItemDto>  updateProduct(@PathVariable(name = "lineItemId") long lineItemId,
													  @PathVariable(name = "productId") long productId
			){	
		LineItemDto lineItem = lineItemService.updateProductInlineItem(lineItemId, productId);
		
		return new ResponseEntity<LineItemDto>(lineItem, HttpStatus.OK);

	}
	
	@DeleteMapping("/{lineItemId}/deleteLineItem")
	public ResponseEntity<String> deleteLineItem(@PathVariable(name = "lineItemId") long lineItemId){
		
		lineItemService.deleteLineItem(lineItemId);
		
		return new ResponseEntity<String>("LineItem was deleted successfully", HttpStatus.OK);
	}
	

}
