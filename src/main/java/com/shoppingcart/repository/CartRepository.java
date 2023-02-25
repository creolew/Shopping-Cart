package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.model.Carts;

public interface CartRepository extends JpaRepository<Carts, Long>{

	List<Carts> findCartsByUserId(Long userId);
}
