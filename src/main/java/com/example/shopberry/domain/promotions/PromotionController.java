package com.example.shopberry.domain.promotions;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.promotions.dto.CreatePromotionRequestDto;
import com.example.shopberry.domain.promotions.dto.PromotionResponseDto;
import com.example.shopberry.domain.promotions.dto.UpdatePromotionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<List<PromotionResponseDto>> getPromotions() {
        List<PromotionResponseDto> promotionResponseDtoList = promotionService.getPromotions();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(promotionResponseDtoList);
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionResponseDto> getPromotionById(@PathVariable Long promotionId) {
        PromotionResponseDto promotionResponseDto = promotionService.getPromotionById(promotionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(promotionResponseDto);
    }

    @PostMapping
    public ResponseEntity<PromotionResponseDto> createPromotion(@RequestBody CreatePromotionRequestDto createPromotionRequestDto) {
        PromotionResponseDto createdPromotionResponseDto = promotionService.createPromotion(createPromotionRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/promotions/" + createdPromotionResponseDto.getPromotionId()))
                .body(createdPromotionResponseDto);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionResponseDto> updatePromotionById(@PathVariable Long promotionId, @RequestBody UpdatePromotionRequestDto updatePromotionRequestDto) {
        PromotionResponseDto updatedPromotionResponseDto = promotionService.updatePromotionById(promotionId, updatePromotionRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPromotionResponseDto);
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<MessageResponseDto> deletePromotionById(@PathVariable Long promotionId) {
        promotionService.deletePromotionById(promotionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Promotion with id " + promotionId + " deleted successfully"));
    }

}
