package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.payload.LineItemDto;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.payload.ProductDtoInCart;

public interface LineItemService {

	LineItemDto createLineItem(LineItemDto lineItemDto, Long productId, Long cartId);
	
	List<ProductDtoInCart> getAllProductsWithCartId(Long cartId);
	
	List<ProductDtoInCart> getAllProductsWithUserIdInLineItem(Long userId);

	
	LineItemDto updateQuantityInLineItemByCartIdAndProductId(Long cartId, Long productId, LineItemDto lineItemDto);
	
	LineItemDto updateProductInlineItem(Long lineItemId, Long productId);
	
	void deleteLineItem(Long lineItemId);


}
