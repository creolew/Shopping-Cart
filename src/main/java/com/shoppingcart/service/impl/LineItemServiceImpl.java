package com.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Carts;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.LineItem;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import com.shoppingcart.payload.CategoryDto;
import com.shoppingcart.payload.LineItemDto;
import com.shoppingcart.payload.ProductDto;
import com.shoppingcart.payload.ProductDtoInCart;
import com.shoppingcart.repository.CartRepository;
import com.shoppingcart.repository.LineItemRepository;
import com.shoppingcart.repository.ProductRepository;
import com.shoppingcart.repository.UserRepository;
import com.shoppingcart.service.LineItemService;

@Service
public class LineItemServiceImpl implements LineItemService {

	private LineItemRepository lineItemRepository;
		
	private ProductRepository productRepository;
	
	private CartRepository cartRepository;

	public LineItemServiceImpl(LineItemRepository lineItemRepository,
			ProductRepository productRepository, CartRepository cartRepository) {
		this.lineItemRepository = lineItemRepository;
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
	}

	@Override
	public LineItemDto createLineItem(LineItemDto lineItemDto, Long cartId, Long productId) {

		Product product = productRepository.findById(productId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Product", "id", productId)
						  	  );
		
		Carts carts = cartRepository.findById(cartId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Cart", "id", cartId)
						  	  );
		
		
		
		LineItem lineItem = mapToEntity(lineItemDto);
		
		lineItem.setCart(carts);
		lineItem.setProduct(product);
		
		LineItem savedLineItem = lineItemRepository.save(lineItem);

		return mapToDto(savedLineItem);
	}


	@Override
	public List<ProductDtoInCart> getAllProductsWithCartId(Long cartId) {
		// TODO Auto-generated method stub
		
		Carts cart = cartRepository.findById(cartId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Cart", "id", cartId)
						  	  );
		
		List <LineItem> lineItems = cart.getCartRela();
		
		
		List<Product> products = lineItems.stream().map(lineItem -> lineItem.getProduct()).collect(Collectors.toList());
		
		List<Integer> quantityList = lineItems.stream().map(lineItem -> lineItem.getQuantity()).collect(Collectors.toList());

		//List<ProductDto> productsDto = products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
		List<ProductDtoInCart> productsDtos = products.stream().map(product -> mapToDtoInCart(product)).collect(Collectors.toList());

		
		for(int i = 0; i < productsDtos.size(); i++) {
			productsDtos.get(i).setQuantity(quantityList.get(i));
			
		}
		
		return productsDtos;
	}
	
	
	@Override
	public List<ProductDtoInCart> getAllProductsWithUserIdInLineItem(Long userId) {
		
		//get cart by user id
		List<Carts> listCarts = cartRepository.findCartsByUserId(userId);

		//get line item  by cart
		List <LineItem> lineItems = new ArrayList<>();
		
		
		for(int i = 0; i< listCarts.size(); i++) {
			
			Long cartId = listCarts.get(i).getId();
			
			System.out.println(cartId);
			
			List <LineItem> tempLineItem = lineItemRepository.findAllByCartId(cartId);
			
			for(LineItem lineItem : tempLineItem) {
				System.out.println(lineItem);
				lineItems.add(lineItem);
			}
	
			System.out.println("size: "+ lineItemRepository.findAllByCartId(cartId).size());
		
		}
		
		List<Product> products = lineItems.stream().map(lineItem -> lineItem.getProduct()).collect(Collectors.toList());
		
		List<Integer> quantityList = lineItems.stream().map(lineItem -> lineItem.getQuantity()).collect(Collectors.toList());

		//List<ProductDto> productsDto = products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
		List<ProductDtoInCart> productsDtos = products.stream().map(product -> mapToDtoInCart(product)).collect(Collectors.toList());

		
		for(int i = 0; i < productsDtos.size(); i++) {
			productsDtos.get(i).setQuantity(quantityList.get(i));
			
		}
		
		return productsDtos;
		
		
		
	}

	
	@Override
	public LineItemDto updateQuantityInLineItemByCartIdAndProductId(Long cartId, Long productId, LineItemDto lineItemDto) {
		
		Carts cart = cartRepository.findById(cartId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Cart", "id", cartId)
						  	  );
		
		Product product = productRepository.findById(productId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Product", "id", productId)
						  	  );
		LineItem lineItem = lineItemRepository.findByCartIdAndProductId(cartId, productId);

		lineItem.setQuantity(lineItemDto.getQuantity());
		
		lineItemRepository.save(lineItem);
		return mapToDto(lineItem);
	}
	
	
	@Override
	public LineItemDto updateProductInlineItem(Long lineItemId, Long productId) {
		
		LineItem lineItem = lineItemRepository.findById(lineItemId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("LineItem", "id", lineItemId)
						  	  );
		
		Product product = productRepository.findById(productId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("Product", "id", productId)
						  	  );
		
		lineItem.setProduct(product);
		
		lineItemRepository.save(lineItem);
		
		return mapToDto(lineItem);
	}
	
	
	
	@Override
	public void deleteLineItem(Long lineItemId) {
		LineItem lineItem = lineItemRepository.findById(lineItemId)
				  .orElseThrow(
						  		() -> new ResourceNotFoundException("LineItem", "id", lineItemId)
						  	  );
		lineItemRepository.delete(lineItem);
		
	}
	
	//convert entity to Dto
	private LineItemDto mapToDto(LineItem lineItem) {
		LineItemDto lineItemDto = new LineItemDto();
		
		lineItemDto.setId(lineItem.getId());
		lineItemDto.setQuantity(lineItem.getQuantity());

		return lineItemDto;
	}
			
	//convert Dto to entity
	private LineItem mapToEntity(LineItemDto lineItemDto) {
		LineItem lineItem = new LineItem();
		
		lineItem.setId(lineItemDto.getId());
		lineItem.setQuantity(lineItemDto.getQuantity());

		return lineItem;
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
			
			
			private ProductDtoInCart mapToDtoInCart(Product product) {
				
				ProductDtoInCart productDtoInCart = new ProductDtoInCart();
				
				productDtoInCart.setId(product.getId());
				productDtoInCart.setAdded_on(product.getAdded_on());
				
				
				productDtoInCart.setCategoryId(product.getCategory().getId());
				
				
				productDtoInCart.setPrice(product.getPrice());
				productDtoInCart.setName(product.getName());
				
				

				return productDtoInCart;
			}


}
