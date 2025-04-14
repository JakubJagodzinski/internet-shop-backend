package com.example.internet_shop.complaints;

import com.example.internet_shop.orders.Order;
import com.example.internet_shop.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "complaints")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private Long complaintId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(length = 500)
    private String info;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(length = 15)
    private String nip;

    @Column(length = 100)
    private String city;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(length = 100)
    private String street;

    @Column(name = "house_number", length = 10)
    private String houseNumber;

    @Column(length = 10)
    private String apartment;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

}
