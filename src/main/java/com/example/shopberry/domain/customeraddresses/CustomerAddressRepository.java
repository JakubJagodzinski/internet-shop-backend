package com.example.shopberry.domain.customeraddresses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    List<CustomerAddress> findAllByCustomer_Id(Long customerId);

    void deleteAllByCustomer_Id(Long customerId);

}
