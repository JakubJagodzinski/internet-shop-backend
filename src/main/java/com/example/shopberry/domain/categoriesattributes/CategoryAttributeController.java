package com.example.shopberry.domain.categoriesattributes;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.categoriesattributes.dto.CategoryAttributeResponseDto;
import com.example.shopberry.domain.categoriesattributes.dto.CreateCategoryAttributeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category-attributes")
@RequiredArgsConstructor
public class CategoryAttributeController {

    private final CategoryAttributeService categoryAttributeService;

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<CategoryAttributeResponseDto>> getCategoryAttributesByCategoryId(@PathVariable Long categoryId) {
        List<CategoryAttributeResponseDto> categoryAttributeResponseDtoList = categoryAttributeService.getCategoryAttributesByCategoryId(categoryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryAttributeResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<CategoryAttributeResponseDto> createCategoryAttribute(@RequestBody CreateCategoryAttributeRequestDto createCategoryAttributeRequestDto) {
        CategoryAttributeResponseDto createdCategoryAttributeResponseDto = categoryAttributeService.createCategoryAttribute(createCategoryAttributeRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/category-attributes/" + createdCategoryAttributeResponseDto.getCategoryId()))
                .body(createdCategoryAttributeResponseDto);
    }

    @DeleteMapping("/by-category/{categoryId}/by-attribute/{attributeId}")
    public ResponseEntity<MessageResponseDto> deleteCategoryAttributeById(@PathVariable Long categoryId, @PathVariable Long attributeId) {
        CategoryAttributeId categoryAttributeId = new CategoryAttributeId(categoryId, attributeId);

        categoryAttributeService.deleteCategoryAttributeById(categoryAttributeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Category attribute with id " + categoryAttributeId + " deleted successfully"));
    }

}
