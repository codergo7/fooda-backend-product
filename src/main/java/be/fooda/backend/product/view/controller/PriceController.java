package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.service.flow.PriceFlow;
import be.fooda.backend.product.service.flow.ProductFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products/price") // https://www.fooda.be/api/v1/products
public class PriceController {

    // INJECT_FLOW_BEAN
    private final PriceFlow priceFlow;

    @GetMapping("/get/default")
    public BigDecimal getDefaultPriceByProductId(@RequestParam("priceId") String productId){
        return priceFlow.findDefaultPriceByProductId(UUID.fromString(productId));
    }

}
