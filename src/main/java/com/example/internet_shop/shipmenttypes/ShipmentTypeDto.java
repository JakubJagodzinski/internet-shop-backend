package com.example.internet_shop.shipmenttypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentTypeDto {

    private Long shipmentTypeId;

    private String shipmentName;

    private Double shipmentCost;

}
