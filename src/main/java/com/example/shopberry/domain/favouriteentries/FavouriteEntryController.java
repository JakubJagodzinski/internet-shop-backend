package com.example.shopberry.domain.favouriteentries;

import com.example.shopberry.common.MessageResponseDto;
import com.example.shopberry.domain.favouriteentries.dto.CreateFavouriteEntryRequestDto;
import com.example.shopberry.domain.favouriteentries.dto.FavouriteEntryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite-entries")
@RequiredArgsConstructor
public class FavouriteEntryController {

    private final FavouriteEntryService favouriteEntryService;

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<FavouriteEntryResponseDto>> getFavouriteEntriesByCustomerId(@PathVariable Long customerId) {
        List<FavouriteEntryResponseDto> favouriteEntryResponseDtoList = favouriteEntryService.getFavouriteEntriesByCustomerId(customerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(favouriteEntryResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<FavouriteEntryResponseDto> createFavouriteEntry(@RequestBody CreateFavouriteEntryRequestDto createFavouriteEntryRequestDto) {
        FavouriteEntryResponseDto createdFavouriteEntryResponseDto = favouriteEntryService.createFavouriteEntry(createFavouriteEntryRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/api/v1/favourite-entries/" + createdFavouriteEntryResponseDto.getCustomerId() + "/" + createdFavouriteEntryResponseDto.getProductId()))
                .body(createdFavouriteEntryResponseDto);
    }

    @DeleteMapping("/by-customer/{customerId}/by-product/{productId}")
    public ResponseEntity<MessageResponseDto> deleteFavouriteEntryByFavouriteEntryId(@PathVariable Long customerId, @PathVariable Long productId) {
        FavouriteEntryId favouriteEntryId = new FavouriteEntryId(productId, customerId);

        favouriteEntryService.deleteFavouriteEntryByFavouriteEntryId(favouriteEntryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MessageResponseDto("Favourite entry with id " + favouriteEntryId + " deleted successfully"));
    }

}
