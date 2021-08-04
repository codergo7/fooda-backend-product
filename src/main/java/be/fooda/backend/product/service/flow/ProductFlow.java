package be.fooda.backend.product.service.flow;

import be.fooda.backend.product.dao.ProductIndexer;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.mapper.ProductMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

// LOMBOK
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

// SPRING
@Service

public class ProductFlow {

    ProductRepository productRepository;
    ProductMapper productMapper;
    ProductIndexer productIndexer;
    ObjectMapper jsonMapper;

    /*
     * Responsibilities of this class: 1- All scenarios (requirements) will be
     * implemented here.. 2- Exceptions will be handled here. 3- Mappers will be
     * used here. 4- Validations for class fields will be checked here. 5- Database
     * transactions will be processed here.
     */

    // CREATE_PRODUCT(REQUEST)
    @Transactional
    public Long createProduct(CreateProductRequest request)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMassages.PRODUCT_IS_REQUIRED.getDescription());
        }

        final var exists = productRepository.existsByTitleAndStoreId(request.getTitle(), request.getStoreId());

        // IF(PRODUCT_EXISTS)
        if (exists) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_ALREADY_EXIST.getDescription());
        }

        // MAP_DTO_TO_ENTITY
        final var entity = productMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        final var savedEntity = productRepository.save(entity);

        // LOG
        final var response = productMapper.toResponse(savedEntity);
        log.info("CREATE A SINGLE PRODUCT: " + "\n\n" + jsonMapper.writeValueAsString(response) + "\n\n");

        // RETURN_SAVED_ID
        return response.getProductId();

    }

    // UPDATE_PRODUCT(UNIQUE_IDENTIFIER, REQUEST)
    @Transactional
    public Long updateProduct(Long id, UpdateProductRequest request)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMassages.FAILED_TO_UPDATE_PRODUCT.getDescription());
        }

        final var oEntity = productRepository.findById(id);

        // IF(PRODUCT_NOT_EXIST)
        if (oEntity.isEmpty()) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
        }

        // MAP_FROM_REQUEST_TO_ENTITY
        final var entity = oEntity.get();
        final var entityToUpdate = productMapper.toEntity(request, entity);

        // UPDATE_FROM_DB
        final var updatedEntity = productRepository.save(entityToUpdate);

        // LOG
        final var response = productMapper.toResponse(updatedEntity);
        log.info("UPDATE A SINGLE PRODUCT BY ID: " + "\n\n" + jsonMapper.writeValueAsString(response) + "\n\n");

        // RETURN_UPDATED_ID
        return updatedEntity.getId();
    }

    // FIND_ALL(PAGE_NO, PAGE_SIZE)
    @Transactional
    public List<ProductResponse> findAll(int pageNo, int pageSize)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        final var pageable = PageRequest.of(pageNo - 1, pageSize);
        final var pages = productRepository.findAll(pageable);

        // MAP_FROM_ENTITIES_TO_RESPONSES
        final var responses = productMapper.toResponses(pages.toList());

        // LOG
        log.info("FIND ALL PRODUCTS: " + "\n\n" + jsonMapper.writeValueAsString(responses) + "\n\n");

        // RETURN_LIST
        return responses;
    }

    // FIND_BY_ID
    @Transactional
    public ProductResponse findById(Long id)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // NULL_CHECK
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        // READ_FROM_DB(ID)
        Optional<ProductEntity> oEntity = productRepository.findById(id);

        // LOG
        log.info("REQUEST -> FIND A SINGLE PRODUCT BY ID: " + "\n\n" + jsonMapper.writeValueAsString(oEntity) + "\n\n");

        if (oEntity.isEmpty()) {
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
        }

        // MAP_ENTITY_TO_RESPONSE
        final var response = productMapper.toResponse(oEntity.get());

        // LOG
        log.info("RESPONSE -> FIND A SINGLE PRODUCT BY ID: " + "\n\n" + jsonMapper.writeValueAsString(response)
                + "\n\n");

        // RETURN
        return response;
    }

    // FIND_BY_TITLE
    @Transactional
    public List<ProductResponse> findByTitle(String title, int pageNo, int pageSize)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        if (Objects.isNull(title)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_TITLE_IS_REQUIRED.getDescription());
        }

        // READ_FROM_DB(ID)
        List<ProductEntity> oEntities = productRepository.findAllByTitleContains(title);

        if (oEntities.isEmpty()) {
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
        }

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ProductEntity> pages = productRepository.findAll(pageable);

        final var responses = productMapper.toResponses(pages.toList());

        // LOG
        log.info("FIND ALL PRODUCTS: " + "\n\n" + jsonMapper.writeValueAsString(responses) + "\n\n");

        // MAP & RETURN
        return responses;
    }

    // EXISTS_BY_ID
    @Transactional
    public Boolean existsById(Long id) throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // NULL_VALIDATION
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        final var response = productRepository.existsById(id);

        // LOG
        final var result = new Exists(Map.of("id", id), response);
        log.info("PRODUCT EXISTS BY UNIQUE FIELDS: " + "\n\n" + jsonMapper.writeValueAsString(result) + "\n\n");

        return response;
    }

    @Transactional
    public void deleteById(Long id) throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // NULL_VALIDATION
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        // TODO: implement delete by id..
    }

    @Transactional
    public void deleteByIdPermanently(Long id)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {
        // NULL_VALIDATION
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        // TODO: implement delete by id..
    }

    @Transactional
    // EXISTS_BY_ID
    public Boolean existsByUniqueFields(String title, Long storeId)
            throws NullPointerException, ResourceNotFoundException, JsonProcessingException {

        // NULL_VALIDATION
        if (Objects.isNull(title)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_TITLE_IS_REQUIRED.getDescription());
        }

        if (Objects.isNull(storeId)) {
            throw new NullPointerException(HttpFailureMassages.STORE_ID_IS_REQUIRED.getDescription());
        }

        final var response = productRepository.existsByTitleAndStoreId(title, storeId);

        // LOG
        final var result = new Exists(Map.of("title", title, "storeId", storeId), response);
        log.info("PRODUCT EXISTS BY UNIQUE FIELDS: " + "\n\n" + jsonMapper.writeValueAsString(result) + "\n\n");

        return response;
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    static class Exists implements Serializable {
        Map<String, Object> params;
        boolean result;
    }

}
