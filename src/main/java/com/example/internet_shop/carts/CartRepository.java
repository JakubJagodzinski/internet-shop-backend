package com.example.internet_shop.carts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {

    List<Cart> findByIdOrderId(Long orderId);

    List<Cart> findByIdProductId(Long productId);

}
