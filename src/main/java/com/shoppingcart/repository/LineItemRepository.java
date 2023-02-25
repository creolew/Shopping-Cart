package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.model.LineItem;
import com.shoppingcart.payload.LineItemDto;

public interface LineItemRepository extends JpaRepository<LineItem, Long>{

	LineItem findByCartIdAndProductId(Long cartId, Long productId);
	List<LineItem> findAllByCartId(Long cartId);
}
