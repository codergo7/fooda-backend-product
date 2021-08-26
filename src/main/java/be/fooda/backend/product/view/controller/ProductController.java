package be.fooda.backend.product.view.controller;

import be.fooda.backend.product.dao.ProductIndexer;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ExistsByUniqueFieldsRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.http.HttpEndpoints;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.model.http.HttpSuccessMassages;
import be.fooda.backend.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

// LOMBOK
@RequiredArgsConstructor

// SPRING
@RestController
@RequestMapping("/api/v1/products") // https://www.fooda.be/api/v1/products

public class ProductController {

    // INJECT_FLOW_BEAN
    ProductRepository productRepository;
    ProductMapper productMapper;
    ProductIndexer productIndexer;

    // CREATE_NEW_PRODUCT
    @Transactional
    @PostMapping(HttpEndpoints.PRODUCTS_POST_SINGLE_TEXT)
    public ResponseEntity<String> create(@RequestBody @Valid @NotNull CreateProductRequest request) {

        // FLOW_AND_RETURN
        return productRepository
                .findByTitleAndStoreId(request.getTitle(), request.getStoreId())
                .map(entity -> productRepository.save(entity))
                .map(entity -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .header("saved_id", String.valueOf(entity.getId()))
                        .body(HttpSuccessMassages.PRODUCT_UPDATED.getDescription()))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, HttpFailureMassages.PRODUCT_ALREADY_EXIST.getDescription());
                });
    }

    // UPDATE_SINGLE_PRODUCT
    @Transactional
    @PutMapping(HttpEndpoints.PRODUCTS_PUT_SINGLE_TEXT)
    public ResponseEntity<String> updateById(@PathVariable("productId") Long productId, @RequestBody @Valid @NotNull UpdateProductRequest request) {

        // FLOW_AND_RETURN
        return productRepository
                .findById(productId)
                .map(entity -> productMapper.toEntity(request, entity))
                .map(entity -> productRepository.save(entity))
                .map(entity -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .header("updated_id", String.valueOf(entity.getId()))
                        .body(HttpSuccessMassages.PRODUCT_UPDATED.getDescription()))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
                });
    }

    // DELETE_BY_ID
    @Transactional
    @DeleteMapping(HttpEndpoints.PRODUCTS_DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@PathVariable("productId") @NotNull Long productId) {

        return productRepository
                .findById(productId)
                .map(entity -> productRepository.makePassive(entity.getId()))
                .map(deleteCount -> deleteCount > 0
                        ? ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(HttpSuccessMassages.PRODUCT_MADE_PASSIVE.getDescription())
                        : ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(HttpFailureMassages.FAILED_TO_MAKE_PRODUCT_PASSIVE.getDescription())
                )
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.FAILED_TO_MAKE_PRODUCT_PASSIVE.getDescription());
                });
    }

    // DELETE_BY_ID_PERMANENTLY
    @Transactional
    @DeleteMapping(HttpEndpoints.PRODUCTS_DELETE_BY_ID_PERMANENTLY)
    public ResponseEntity<String> deleteByIdPermanently(@PathVariable("productId") @NotNull Long productId) {

        return productRepository
                .findById(productId)
                .map(entity -> {
                    productRepository.deleteById(productId);
                    return ResponseEntity
                            .status(HttpStatus.ACCEPTED)
                            .body(HttpSuccessMassages.PRODUCT_DELETED.getDescription());
                })
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
                });
    }

    // @PatchMapping // UPDATE PRODUCT(S) BUT NOT ALL THE FIELDS

    // GET_ALL
    @Transactional
    @GetMapping(HttpEndpoints.PRODUCTS_FIND_ALL_TEXT)
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(value = HttpEndpoints.PAGE_NUMBER_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_NUMBER_DEFAULT_VALUE) Integer pageNo,
            @RequestParam(value = HttpEndpoints.PAGE_SIZE_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_SIZE_DEFAULT_VALUE) Integer pageSize) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        productRepository
                                .findAllByIsActive(true, PageRequest.of(pageNo - 1, pageSize))
                                .stream()
                                .map(entity -> productMapper.toResponse(entity))
                                .collect(Collectors.toUnmodifiableList())
                );
    }

    // GET_BY_ID
    @Transactional
    @GetMapping(HttpEndpoints.PRODUCTS_FIND_BY_ID_TEXT)
    public ResponseEntity<ProductResponse> findById(@PathVariable("productId") @NotNull Long productId) {

        return productRepository
                .findById(productId)
                .map(entity -> productMapper.toResponse(entity))
                .map(response -> ResponseEntity.status(HttpStatus.FOUND).body(response))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
                });
    }

    // SEARCH(KEYWORDS)
    @Transactional
    @GetMapping(HttpEndpoints.PRODUCTS_SEARCH_TEXT)
    public ResponseEntity<List<ProductResponse>> search(
            @PathVariable("keyword") @NotNull String keyword,
            @RequestParam(
                    value = HttpEndpoints.PAGE_NUMBER_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_NUMBER_DEFAULT_VALUE) Integer pageNo,
            @RequestParam(
                    value = HttpEndpoints.PAGE_SIZE_TEXT,
                    required = false, defaultValue = HttpEndpoints.PAGE_SIZE_DEFAULT_VALUE) Integer pageSize) {

        // FLOW_AND_RETURN
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        productIndexer
                                .search(PageRequest.of(pageNo - 1, pageSize), keyword)
                                .stream()
                                .map(entity -> productMapper.toResponse(entity))
                                .collect(Collectors.toUnmodifiableList())
                );
    }

    // EXISTS_BY_ID
    @Transactional
    @GetMapping(HttpEndpoints.PRODUCTS_FIND_EXISTS_BY_ID_TEXT)
    public ResponseEntity<String> existsById(@PathVariable("productId") @NotNull Long productId) {

        return productRepository.existsById(productId)
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMassages.PRODUCT_EXISTS.getDescription())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription());
    }

    // EXISTS_BY_UNIQUE_FIELDS
    @Transactional
    @GetMapping(HttpEndpoints.PRODUCT_EXISTS_BY_UNIQUE_FIELDS)
    public ResponseEntity<String> existsByUniqueFields(@RequestBody ExistsByUniqueFieldsRequest request) {

        return productRepository.existsByTitleAndStoreId(request.getTitle(), request.getStoreId())
                ? ResponseEntity.status(HttpStatus.FOUND).body(HttpSuccessMassages.PRODUCT_EXISTS.getDescription())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpFailureMassages.PRODUCT_DOES_NOT_EXIST.getDescription());
    }


}
