package be.fooda.backend.product.view.controller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.flow.ProductFlow;
import lombok.RequiredArgsConstructor;

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
    public ResponseEntity createProduct(@RequestBody @Valid CreateProductRequest request) {

        // CREATE_FLOW
        try {
            productFlow.createProduct(request);
            // RETURN_SUCCESS
            return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_CREATED.getDescription());
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    // UPDATE_SINGLE_PRODUCT
    @PutMapping(PUT_SINGLE)
    public ResponseEntity updateProduct(@RequestParam("productId") String id,
            @RequestBody @Valid UpdateProductRequest request) {

        // UPDATE_FLOW
        try {
            productFlow.updateProduct(UUID.fromString(id), request);
            // RETURN_SUCCESS
            return ResponseEntity.status(HttpStatus.CREATED).body(HttpSuccessMassages.PRODUCT_UPDATED.getDescription());
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    // DELETE_BY_ID
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity deleteById(@RequestParam("productId") String id) {

        try {
            productFlow.deleteById(UUID.fromString(id));
            // RETURN_SUCCESS
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(HttpSuccessMassages.PRODUCT_DELETED.getDescription());
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

    }

    // DELETE_BY_ID_PERMANENTLY
    @DeleteMapping(DELETE_BY_ID_PERMANENTLY)
    public ResponseEntity deleteByIdPermanently(@RequestParam("productId") String id) {

        try {
            productFlow.deleteByIdPermanently(UUID.fromString(id));
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(HttpSuccessMassages.PRODUCT_DELETED.getDescription());
    }

    // @PatchMapping // UPDATE PRODUCT(S) BUT NOT ALL THE FIELDS

    // GET_ALL
    @GetMapping(GET_ALL)
    public ResponseEntity findAllProducts(@RequestParam(value = PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = PAGE_SIZE, required = false) Integer pageSize) {

        // SET DEFAULT VALUES ..
        pageNo = PAGE_NUMBER_DEFAULT_VALUE;
        pageSize = PAGE_SIZE_DEFAULT_VALUE;

        final var responses = new LinkedList<>();

        // START_SELECT_FLOW
        try {
            // RETURN_ALL_PRODUCTS_IN_PAGES
            responses.addAll(productFlow.findAll(pageNo, pageSize));
            return ResponseEntity.status(HttpStatus.FOUND).body(responses);
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    // GET_BY_ID
    @GetMapping(GET_BY_ID)
    public ResponseEntity findProductById(@RequestParam("productId") String id) {

        final var response = new Object(){
            public ProductResponse product = null;
        };
        
        // START_SELECT_FLOW
        try {
            response.product = productFlow.findById(UUID.fromString(id));
              // RETURN_SUCCESS
            return ResponseEntity.status(HttpStatus.FOUND).body(Objects.requireNonNull(response.product));
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    // SEARCH(KEYWORDS)
    @GetMapping(GET_SEARCH)
    public ResponseEntity search(@RequestParam Map<String, String> keywords) {

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.FOUND).body(Collections.EMPTY_LIST);
    }

    // EXISTS_BY_ID
    @GetMapping(GET_EXISTS_BY_ID)
    public ResponseEntity existsById(@RequestParam("productId") String id) {

        final var response = new Object() {
            public boolean exists = false;
        };

        // START_SELECT_FLOW
        try {
            response.exists = productFlow.existsById(UUID.fromString(id));
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        final var body = (response.exists == Boolean.TRUE) ? HttpSuccessMassages.PRODUCT_EXISTS.getDescription()
                : HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription();

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }

    // EXISTS_BY_UNIQUE_FIELDS
    @GetMapping(GET_EXISTS_BY_UNIQUE_FIELDS)
    public ResponseEntity existsByUniqueFields(@RequestParam("name") String name,
            @RequestParam("storeId") String storeId) {

        final var response = new Object(){
            public boolean exists = false;
        }; 

         // START_SELECT_FLOW
         try {
            response.exists = productFlow.existsByUniqueFields(name, storeId);
        } catch (NullPointerException | ResourceNotFoundException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        final var body = (response.exists == Boolean.TRUE) ? HttpSuccessMassages.PRODUCT_EXISTS.getDescription()
                : HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription();

        // RETURN_SUCCESS
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(body);
    }

}
