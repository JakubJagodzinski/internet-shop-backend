package com.example.shopberry.domain.productattributes;

import com.example.shopberry.domain.attributes.Attribute;
import com.example.shopberry.domain.products.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_attributes")
public class ProductAttribute {

    @EmbeddedId
    private ProductAttributeId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_attribute_product"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne
    @MapsId("attributeId")
    @JoinColumn(name = "attribute_id", foreignKey = @ForeignKey(name = "fk_product_attribute_attribute"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attribute attribute;

    @Column(nullable = false)
    private Double value;

}
