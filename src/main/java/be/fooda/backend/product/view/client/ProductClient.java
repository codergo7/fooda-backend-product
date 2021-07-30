package be.fooda.backend.product.view.client;

import be.fooda.backend.product.model.remote.ProductClientPriceResponse;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.exception.UnexpectedStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProductClient{

    private final static String PRODUCT_API_URL = "http://localhost:8081/api/v1/products";

    private final RestTemplate restClient;

    // PRODUCTION_URL : https://www.fooda.be/api/v1/products
    public BigDecimal getDefaultPrice(String productId) throws ResourceNotFoundException {

        HttpEntity emptyEntity = HttpEntity.EMPTY;

        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("productId", productId);

        ResponseEntity<ProductClientPriceResponse> response = restClient.exchange(PRODUCT_API_URL + "/get/one", HttpMethod.GET, emptyEntity, ProductClientPriceResponse.class, requestParams);

        HttpStatus status = response.getStatusCode();

        if(!status.equals(HttpStatus.FOUND) && !status.equals(HttpStatus.NOT_FOUND)){
            throw new UnexpectedStatusException();
        }

        if(status.equals(HttpStatus.NOT_FOUND)){
            throw new ResourceNotFoundException("Product not found");
        }

        if(!response.hasBody()){
            throw new ResourceNotFoundException("The response does NOT contain a body!");
        }

        ProductClientPriceResponse price = response.getBody();

        if(Objects.isNull(price.getAmount())){
            throw new ResourceNotFoundException("The price does NOT contain an value.");
        }

        return price.getAmount();
    }

}
