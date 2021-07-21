package be.fooda.backend.product.service.flow;

import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.http.HttpFailureMassages;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductFlow {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /*
     * Responsibilities of this class: 1- All scenarios (requirements) will be
     * implemented here.. 2- Exceptions will be handled here. 3- Mappers will be
     * used here. 4- Validations for class fields will be checked here. 5- Database
     * transactions will be processed here.
     */

    // CREATE_PRODUCT(REQUEST)
    public void createProduct(CreateProductRequest request) throws NullPointerException, ResourceNotFoundException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMassages.FAILED_TO_CREATE_PRODUCT.getDescription());
        }

        // IF(PRODUCT_EXISTS)
        boolean exists = productRepository.existsByTitleAndStoreId(request.getTitle(),
                request.getStoreId());

        if (exists) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_ALREADY_EXIST.getDescription());
        }

        // MAP_DTO_TO_ENTITY
        ProductEntity entity = productMapper.toEntity(request);

        // SAVE_TO_DB(ENTITY)
        productRepository.save(entity);

    }

    // UPDATE_PRODUCT(UNIQUE_IDENTIFIER, REQUEST)
    public void updateProduct(UUID id, UpdateProductRequest request)
            throws NullPointerException, ResourceNotFoundException {

        // IF(NULL)
        if (Objects.isNull(request)) {
            // THROW_EXCEPTION
            throw new NullPointerException(HttpFailureMassages.FAILED_TO_UPDATE_PRODUCT.getDescription());
        }

        Optional<ProductEntity> oEntity = productRepository.findById(id);

        // IF(PRODUCT_NOT_EXIST)
        if (oEntity.isEmpty()) {
            // THROW_EXCEPTION
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
        }

        // MAP_FROM_REQUEST_TO_ENTITY
        ProductEntity entity = oEntity.get();
        ProductEntity entityToUpdate = productMapper.toEntity(request, entity);

        // UPDATE_FROM_DB
        productRepository.save(entityToUpdate);

    }

    // FIND_ALL(PAGE_NO, PAGE_SIZE)
    public List<ProductResponse> findAll(int pageNo, int pageSize)
            throws NullPointerException, ResourceNotFoundException {

        // READ_FROM_DB(PAGE_NO, PAGE_SIZE)
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ProductEntity> pages = productRepository.findAll(pageable);

        // MAP & RETURN
        return productMapper.toResponses(pages.toList());
    }

    // FIND_BY_ID
    public ProductResponse findById(UUID id) throws NullPointerException, ResourceNotFoundException {

        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        // READ_FROM_DB(ID)
        Optional<ProductEntity> oEntity = productRepository.findById(id);

        if (oEntity.isEmpty()) {
            throw new ResourceNotFoundException(HttpFailureMassages.PRODUCT_NOT_FOUND.getDescription());
        }

        // MAP & RETURN
        return productMapper.toResponse(oEntity.get());
    }

    // FIND_BY_TITLE
    public List<ProductResponse> findByTitle(String title, int pageNo, int pageSize)
            throws NullPointerException, ResourceNotFoundException {

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

        // MAP & RETURN
        return productMapper.toResponses(pages.toList());
    }

    // EXISTS_BY_ID
    public Boolean existsById(UUID id) throws NullPointerException, ResourceNotFoundException {
        
        if (Objects.isNull(id)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_ID_IS_REQUIRED.getDescription());
        }

        return productRepository.existsById(id);
    }

     // EXISTS_BY_ID
     public Boolean existsByUniqueFields(String title, String storeId) throws NullPointerException, ResourceNotFoundException {
        
        if (Objects.isNull(title)) {
            throw new NullPointerException(HttpFailureMassages.PRODUCT_TITLE_IS_REQUIRED.getDescription());
        }

        if (Objects.isNull(storeId)) {
            throw new NullPointerException(HttpFailureMassages.STORE_ID_IS_REQUIRED.getDescription());
        }

        return productRepository.existsByTitleAndStoreId(title, storeId);
    }

}
