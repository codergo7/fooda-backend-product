package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.PriceRepository;
import be.fooda.backend.product.model.dto.PriceResponse;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.service.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/find/one")
    public ResponseEntity<PriceResponse> findById(@RequestParam("priceId") Long priceId) {

        // FLOW_AND_RETURN
        return priceRepository
                .findById(priceId)
                .map(entity -> priceMapper.toResponse(entity))
                .map(response -> ResponseEntity.status(HttpStatus.FOUND).body(response))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.NO_PRODUCT_PRICE_IS_FOUND.getDescription());
                });
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<PriceResponse>> findAllByProductId(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize,
            @RequestParam("productId") Long productId) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        priceRepository
                                .findByProductId(productId, PageRequest.of(pageNo - 1, pageSize))
                                .map(entity -> priceMapper.toResponse(entity))
                                .toList()
                );
    }

    @GetMapping("/find/default")
    public ResponseEntity<PriceResponse> getDefaultPriceByProductId(@RequestParam("priceId") @NotNull Long productId) {

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
