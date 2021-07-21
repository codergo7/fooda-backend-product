package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.flow.ProductFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products") // https://www.fooda.be/api/v1/products
public class ProductController {

    private static final int PAGE_SIZE_PER_RESULT = 25;
    private static final int DEFAULT_PAGE_NO = 0;

    private static final String PAGE_NUMBER = "pageNo";
    private static final Integer PAGE_NUMBER_DEFAULT_VALUE = 1;
    private static final String PAGE_SIZE = "pageSize";
    private static final Integer PAGE_SIZE_DEFAULT_VALUE = 50;

    private static final String GET_ALL = "get/all";
    private static final String GET_SEARCH = "search";
    private static final String GET_FILTER = "filter";
    private static final String POST_SINGLE = "add/one";
    private static final String POST_ALL = "add/all";
    private static final String GET_BY_ID = "get/one";
    private static final String GET_EXISTS_BY_ID = "exists/one";
    private static final String GET_EXISTS_BY_UNIQUE_FIELDS = "exists/unique";
    private static final String PUT_SINGLE = "edit/one";
    private static final String PUT_ALL = "edit/all";
    private static final String DELETE_BY_ID = "delete/one";
    private static final String DELETE_BY_ID_PERMANENTLY = "delete/one/permanent";

    // INJECT_FLOW_BEAN
    private final ProductFlow productFlow;

    // RESPONSE_ENTITY = STATUS, HEADERS, BODY

    // CREATING_NEW_PRODUCT
    @PostMapping(POST_SINGLE)
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateProductRequest request) {

        // CREATE_FLOW
        productFlow.createProduct(request);

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_CREATED.getDescription());
    }

    // UPDATE_SINGLE_PRODUCT
    @PutMapping(PUT_SINGLE)
    public ResponseEntity<String> updateProduct(@RequestParam("productId") UUID id,
            @RequestBody @Valid UpdateProductRequest request) {

        // UPDATE_FLOW
        productFlow.updateProduct(id, request);

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_UPDATED.getDescription());
    }

    // DELETE_BY_ID
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@RequestParam("productId") UUID id) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.PRODUCT_DELETED.getDescription());
    }

    // DELETE_BY_ID_PERMANENTLY
    @DeleteMapping(DELETE_BY_ID_PERMANENTLY)
    public ResponseEntity<String> deleteByIdPermanently(@RequestParam("productId") UUID id) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.PRODUCT_DELETED.getDescription());
    }

    // @PatchMapping // UPDATE PRODUCT(S) BUT NOT ALL THE FIELDS

    // GET_ALL
    @GetMapping(GET_ALL)
    public ResponseEntity<List<ProductResponse>> findAllProducts(
            @RequestParam(value = PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = PAGE_SIZE, required = false) Integer pageSize) {

        // SET DEFAULT VALUES ..
        pageNo = PAGE_NUMBER_DEFAULT_VALUE;
        pageSize = PAGE_SIZE_DEFAULT_VALUE;

        // START_SELECT_FLOW
        final List<ProductResponse> responses = productFlow.findAll(pageNo, pageSize);

        // RETURN_ALL_PRODUCTS_IN_PAGES
        return ResponseEntity.status(HttpStatus.FOUND).body(responses);
    }

    // GET_BY_ID
    @GetMapping(GET_BY_ID)
    public ResponseEntity<ProductResponse> findProductById(@RequestParam("productId") UUID id) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.FOUND).body(new ProductResponse());
    }

    // SEARCH(KEYWORDS)
    @GetMapping(GET_SEARCH)
    public ResponseEntity<List<ProductResponse>> search(@RequestParam Map<String, String> keywords) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.FOUND).body(Collections.EMPTY_LIST);
    }

    // EXISTS_BY_ID
    @GetMapping(GET_EXISTS_BY_ID)
    public ResponseEntity<String> existsById(@RequestParam("productId") UUID id) {

        final var exists = productFlow.existsById(id);
        final var body = (exists == Boolean.TRUE) 
                ? HttpSuccessMassages.PRODUCT_EXISTS.getDescription()
                : HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription();


        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }

     // EXISTS_BY_UNIQUE_FIELDS
     @GetMapping(GET_EXISTS_BY_UNIQUE_FIELDS)
     public ResponseEntity<String> existsByUniqueFields(@RequestParam("name") String name,  @RequestParam("storeId") String storeId) {

        final var exists = productFlow.existsByUniqueFields(name, storeId);
        final var body = (exists == Boolean.TRUE) 
                ? HttpSuccessMassages.PRODUCT_EXISTS.getDescription()
                : HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription();

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }

}
