package com.example.shopberry.domain.categoriesattributes;

import com.example.shopberry.domain.attributes.Attribute;
import com.example.shopberry.domain.attributes.AttributeRepository;
import com.example.shopberry.domain.categories.Category;
import com.example.shopberry.domain.categories.CategoryRepository;
import com.example.shopberry.domain.categoriesattributes.dto.CategoryAttributeDtoMapper;
import com.example.shopberry.domain.categoriesattributes.dto.CategoryAttributeResponseDto;
import com.example.shopberry.domain.categoriesattributes.dto.CreateCategoryAttributeRequestDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAttributeService {

    private final CategoryAttributeRepository categoryAttributeRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;

    private final CategoryAttributeDtoMapper categoryAttributeDtoMapper;

    private static final String CATEGORY_ATTRIBUTE_NOT_FOUND_MESSAGE = "Category attribute not found";
    private static final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found";
    private static final String ATTRIBUTE_NOT_FOUND_MESSAGE = "Attribute not found";
    private static final String ATTRIBUTE_ALREADY_ASSIGNED_TO_THIS_CATEGORY_MESSAGE = "Attribute already assigned to this category";

    @Transactional
    public List<CategoryAttributeResponseDto> getCategoryAttributesByCategoryId(Long categoryId) throws EntityNotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }

        return categoryAttributeDtoMapper.toDtoList(categoryAttributeRepository.findByCategory_CategoryId(categoryId));
    }

    @Transactional
    public CategoryAttributeResponseDto createCategoryAttribute(CreateCategoryAttributeRequestDto createCategoryAttributeRequestDto) throws EntityNotFoundException, IllegalArgumentException {
        Category category = categoryRepository.findById(createCategoryAttributeRequestDto.getCategoryId()).orElse(null);

        if (category == null) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }

        Attribute attribute = attributeRepository.findById(createCategoryAttributeRequestDto.getAttributeId()).orElse(null);

        if (attribute == null) {
            throw new EntityNotFoundException(ATTRIBUTE_NOT_FOUND_MESSAGE);
        }

        CategoryAttributeId categoryAttributeId = new CategoryAttributeId(createCategoryAttributeRequestDto.getCategoryId(), createCategoryAttributeRequestDto.getAttributeId());

        if (categoryAttributeRepository.existsById(categoryAttributeId)) {
            throw new IllegalArgumentException(ATTRIBUTE_ALREADY_ASSIGNED_TO_THIS_CATEGORY_MESSAGE);
        }

        CategoryAttribute categoryAttribute = new CategoryAttribute();

        categoryAttribute.setId(categoryAttributeId);
        categoryAttribute.setCategory(category);
        categoryAttribute.setAttribute(attribute);

        return categoryAttributeDtoMapper.toDto(categoryAttributeRepository.save(categoryAttribute));
    }

    @Transactional
    public void deleteCategoryAttributeById(CategoryAttributeId categoryAttributeId) throws EntityNotFoundException {
        if (!categoryRepository.existsById(categoryAttributeId.getCategoryId())) {
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE);
        }

        if (!attributeRepository.existsById(categoryAttributeId.getAttributeId())) {
            throw new EntityNotFoundException(ATTRIBUTE_NOT_FOUND_MESSAGE);
        }

        if (!categoryAttributeRepository.existsById(categoryAttributeId)) {
            throw new EntityNotFoundException(CATEGORY_ATTRIBUTE_NOT_FOUND_MESSAGE);
        }

        categoryAttributeRepository.deleteById(categoryAttributeId);
    }

}
