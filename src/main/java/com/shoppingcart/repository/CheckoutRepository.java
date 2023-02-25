package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{

}
