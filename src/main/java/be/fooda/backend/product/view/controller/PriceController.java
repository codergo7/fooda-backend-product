package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.model.dto.PriceResponse;
import be.fooda.backend.product.service.flow.PriceFlow;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Set;

// LOMBOK
@Slf4j
@RequiredArgsConstructor

// SPRING
@RestController
@RequestMapping("/api/v1/product/prices") // https://www.fooda.be/api/v1/product/prices

public class PriceController {

    // INJECT_FLOW_BEAN
    private final PriceFlow priceFlow;

    @GetMapping("/find/one")
    public ResponseEntity findById(@RequestParam("priceId") Long priceId) {

        final var response = new Object(){
            PriceResponse price;
        };

        try {
            response.price = priceFlow.findById(priceId);
            return ResponseEntity.status(HttpStatus.FOUND).body(response.price);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity findAllByProductId(@RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = "25", required = false) int pageSize,
                                             @RequestParam("productId") Long productId){

        final var response = new Object(){
            Set<PriceResponse> prices;
        };

        // PRICE_FLOW
        try {
            response.prices = priceFlow.findAllByProductId(pageNo, pageSize, productId);
            return ResponseEntity.status(HttpStatus.FOUND).body(response.prices);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

    @GetMapping("/find/default")
    public ResponseEntity getDefaultPriceByProductId(@RequestParam("priceId") Long productId){
        try {
            BigDecimal defaultPrice = priceFlow.findDefaultPriceByProductId(productId);
            return ResponseEntity.status(HttpStatus.FOUND).body(defaultPrice);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

}
