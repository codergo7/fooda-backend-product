package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.PriceRepository;
import be.fooda.backend.product.model.dto.PriceResponse;
import be.fooda.backend.product.model.http.HttpEndpoints;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.service.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor

// SPRING
@RestController
@RequestMapping("/api/v1/product/prices") // https://www.fooda.be/api/v1/product/prices

public class PriceController {

    PriceRepository priceRepository;
    PriceMapper priceMapper;

    @GetMapping(HttpEndpoints.PRICES_FIND_BY_ID_TEXT)
    public ResponseEntity<PriceResponse> findById(@PathVariable Long productId, @PathVariable Long priceId) {

        // FLOW_AND_RETURN
        return priceRepository
                .findById(priceId)
                .map(entity -> priceMapper.toResponse(entity))
                .map(response -> ResponseEntity.status(HttpStatus.FOUND).body(response))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.NO_PRODUCT_PRICE_IS_FOUND.getDescription());
                });
    }

    @GetMapping(HttpEndpoints.PRICES_FIND_ALL_BY_PRODUCT_ID_TEXT)
    public ResponseEntity<List<PriceResponse>> findAllByProductId(
            @RequestParam(defaultValue = "1", required = false) int pageNo,
            @RequestParam(defaultValue = "25", required = false) int pageSize,
            @RequestParam Long productId) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        priceRepository
                                .findByProductId(productId, PageRequest.of(pageNo - 1, pageSize))
                                .map(entity -> priceMapper.toResponse(entity))
                                .toList()
                );
    }

    @GetMapping(HttpEndpoints.PRICES_FIND_BY_ID_TEXT)
    public ResponseEntity<PriceResponse> findDefaultPriceByProductId(@RequestParam("productId") @NotNull Long productId) {

        // FLOW_WITH_RETURN
        return priceRepository
                .findByProductIdAndDefault(productId)
                .map(entity -> priceMapper.toResponse(entity))
                .map(response -> ResponseEntity.status(HttpStatus.FOUND).body(response))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.NO_PRODUCT_PRICE_IS_FOUND.getDescription());
                });

    }

}
