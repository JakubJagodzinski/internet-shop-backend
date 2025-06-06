package com.example.shopberry.domain.orderstatuses.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderStatusRequestDto {

    @JsonProperty("order_status_name")
    private String orderStatusName;

}
