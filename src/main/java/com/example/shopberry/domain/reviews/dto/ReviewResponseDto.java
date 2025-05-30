package com.example.shopberry.domain.reviews.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    @JsonProperty("review_id")
    private Long reviewId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("rating_value")
    private Double ratingValue;

    @JsonProperty("review_text")
    private String reviewText;

    @JsonProperty("reviewed_at")
    private LocalDateTime reviewedAt;

    @JsonProperty("is_approved")
    private Boolean isApproved;

}
