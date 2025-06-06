package com.example.shopberry.domain.productattributes;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.productattributes.dto.CreateProductAttributeRequestDto;
import com.example.shopberry.domain.productattributes.dto.ProductAttributeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService productAttributeService;

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ProductAttributeResponseDto>> getProductAttributesByProductId(@PathVariable Long productId) {
        List<ProductAttributeResponseDto> productAttributeResponseDtoList = productAttributeService.getProductAttributesByProductId(productId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productAttributeResponseDtoList);
    }

    @GetMapping("/by-attribute/{attributeId}")
    public ResponseEntity<List<ProductAttributeResponseDto>> getProductAttributesByAttributeId(@PathVariable Long attributeId) {
        List<ProductAttributeResponseDto> productAttributeResponseDtoList = productAttributeService.getProductAttributesByAttributeId(attributeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productAttributeResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<ProductAttributeResponseDto> createProductAttribute(@RequestBody CreateProductAttributeRequestDto createProductAttributeRequestDto) {
        ProductAttributeResponseDto createdProductAttributeResponseDto = productAttributeService.createProductAttribute(createProductAttributeRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/product-attributes/" + createdProductAttributeResponseDto.getProductId() + "/" + createdProductAttributeResponseDto.getAttributeId()))
                .body(createdProductAttributeResponseDto);
    }

    @DeleteMapping("/by-product/{productId}/by-attribute/{attributeId}")
    public ResponseEntity<MessageResponseDto> deleteProductAttributeById(@PathVariable Long productId, @PathVariable Long attributeId) {
        ProductAttributeId productAttributeId = new ProductAttributeId(productId, attributeId);

        productAttributeService.deleteProductAttributeById(productAttributeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Product attribute with id " + productAttributeId + " deleted successfully"));
    }

}
