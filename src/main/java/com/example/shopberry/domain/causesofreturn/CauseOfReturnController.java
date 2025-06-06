package com.example.shopberry.domain.causesofreturn;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.causesofreturn.dto.CauseOfReturnResponseDto;
import com.example.shopberry.domain.causesofreturn.dto.CreateCauseOfReturnRequestDto;
import com.example.shopberry.domain.causesofreturn.dto.UpdateCauseOfReturnRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/causes-of-return")
@RequiredArgsConstructor
public class CauseOfReturnController {

    private final CauseOfReturnService causeOfReturnService;

    @GetMapping
    public ResponseEntity<List<CauseOfReturnResponseDto>> getCausesOfReturn() {
        List<CauseOfReturnResponseDto> causeOfReturnResponseDtoList = causeOfReturnService.getCausesOfReturn();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(causeOfReturnResponseDtoList);
    }

    @GetMapping("/{causeOfReturnId}")
    public ResponseEntity<CauseOfReturnResponseDto> getCauseOfReturnById(@PathVariable Long causeOfReturnId) {
        CauseOfReturnResponseDto causeOfReturnResponseDto = causeOfReturnService.getCauseOfReturnById(causeOfReturnId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(causeOfReturnResponseDto);
    }

    @PostMapping
    public ResponseEntity<CauseOfReturnResponseDto> createCauseOfReturn(@RequestBody CreateCauseOfReturnRequestDto createCauseOfReturnRequestDto) {
        CauseOfReturnResponseDto createdCauseOfReturnResponseDto = causeOfReturnService.createCauseOfReturn(createCauseOfReturnRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/causes-of-return/" + createdCauseOfReturnResponseDto.getCauseOfReturnId()))
                .body(createdCauseOfReturnResponseDto);
    }

    @PutMapping("/{causeOfReturnId}")
    public ResponseEntity<CauseOfReturnResponseDto> updateCauseOfReturnById(@PathVariable Long causeOfReturnId, @RequestBody UpdateCauseOfReturnRequestDto updateCauseOfReturnRequestDto) {
        CauseOfReturnResponseDto updatedCauseOfReturnResponseDto = causeOfReturnService.updateCauseOfReturnById(causeOfReturnId, updateCauseOfReturnRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCauseOfReturnResponseDto);
    }

    @DeleteMapping("/{causeOfReturnId}")
    public ResponseEntity<MessageResponseDto> deleteCauseOfReturnById(@PathVariable Long causeOfReturnId) {
        causeOfReturnService.deleteCauseOfReturnById(causeOfReturnId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Cause of return with id " + causeOfReturnId + " deleted successfully"));
    }

}
