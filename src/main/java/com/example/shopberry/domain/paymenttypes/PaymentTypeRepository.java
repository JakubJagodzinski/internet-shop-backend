package com.example.shopberry.domain.paymenttypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

    boolean existsByPaymentName(String paymentName);

    PaymentType findByPaymentName(String paymentName);

}
