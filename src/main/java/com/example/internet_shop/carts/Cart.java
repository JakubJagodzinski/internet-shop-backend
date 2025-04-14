package com.example.internet_shop.carts;

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
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Cart {

    @EmbeddedId
    private CartId cartId;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

}
