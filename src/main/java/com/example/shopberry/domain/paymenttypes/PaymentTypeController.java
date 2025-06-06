package com.example.shopberry.domain.paymenttypes;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.paymenttypes.dto.CreatePaymentTypeRequestDto;
import com.example.shopberry.domain.paymenttypes.dto.PaymentTypeResponseDto;
import com.example.shopberry.domain.paymenttypes.dto.UpdatePaymentTypeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-types")
@RequiredArgsConstructor
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    @GetMapping
    public ResponseEntity<List<PaymentTypeResponseDto>> getPaymentTypes() {
        List<PaymentTypeResponseDto> paymentTypeResponseDtoList = paymentTypeService.getPaymentTypes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentTypeResponseDtoList);
    }

    @GetMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentTypeResponseDto> getPaymentTypeById(@PathVariable Long paymentTypeId) {
        PaymentTypeResponseDto paymentTypeResponseDto = paymentTypeService.getPaymentTypeById(paymentTypeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentTypeResponseDto);
    }

    @PostMapping
    public ResponseEntity<PaymentTypeResponseDto> createPaymentType(@RequestBody CreatePaymentTypeRequestDto createPaymentTypeRequestDto) {
        PaymentTypeResponseDto createdPaymentTypeResponseDto = paymentTypeService.createPaymentType(createPaymentTypeRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/payment-types/" + createdPaymentTypeResponseDto.getPaymentTypeId()))
                .body(createdPaymentTypeResponseDto);
    }

    @PutMapping("/{paymentTypeId}")
    public ResponseEntity<PaymentTypeResponseDto> updatePaymentTypeById(@PathVariable Long paymentTypeId, @RequestBody UpdatePaymentTypeRequestDto updatePaymentTypeRequestDto) {
        PaymentTypeResponseDto updatedPaymentTypeResponseDto = paymentTypeService.updatePaymentTypeById(paymentTypeId, updatePaymentTypeRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedPaymentTypeResponseDto);
    }

    @DeleteMapping("/{paymentTypeId}")
    public ResponseEntity<MessageResponseDto> deletePaymentTypeById(@PathVariable Long paymentTypeId) {
        paymentTypeService.deletePaymentTypeById(paymentTypeId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Payment type with id: " + paymentTypeId + " deleted successfully"));
    }

}
