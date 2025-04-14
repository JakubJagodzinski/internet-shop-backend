package com.example.internet_shop.productreturns;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReturnRepository extends JpaRepository<ProductReturn, Long> {
}
