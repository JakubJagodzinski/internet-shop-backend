package com.example.internet_shop.reviews;

import com.example.internet_shop.customers.Customer;
import com.example.internet_shop.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    // TODO when user is deleted, replace with anonymous user
    private Customer customer;

    @Column(name = "rating_value", nullable = false)
    private Double ratingValue;

    @Column(name = "review_text", length = 1000)
    private String reviewText = "";

    @Column(name = "reviewed_at", nullable = false)
    private LocalDateTime reviewedAt = LocalDateTime.now();

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved = false;

}
