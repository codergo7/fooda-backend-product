package be.fooda.backend.product.service.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import be.fooda.backend.product.model.dto.CreateProductRequest;
import be.fooda.backend.product.model.dto.ProductResponse;
import be.fooda.backend.product.model.dto.UpdateProductRequest;
import be.fooda.backend.product.model.entity.ProductEntity;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest source);

    Set<ProductEntity> toEntities(Set<CreateProductRequest> sourceSet);

    List<ProductEntity> toEntities(List<CreateProductRequest> sourceList);

    ProductEntity toEntity(UpdateProductRequest source, @MappingTarget ProductEntity target);

    List<ProductEntity> toEntities(List<UpdateProductRequest> sources, @MappingTarget List<ProductEntity> targets);

    CreateProductRequest toRequest(ProductEntity source);

    List<CreateProductRequest> toRequests(List<ProductEntity> sources);

    UpdateProductRequest toRequest(ProductEntity source, @MappingTarget UpdateProductRequest target);

    // FIELD_LEVEL_CUSTOMIZATIONS
    @Mapping(source = "storeId", target = "store.storeId")
    @Mapping(source = "defaultImageId", target = "defaultImage.mediaId")
    @Mapping(source = "id", target = "productId")
    ProductResponse toResponse(ProductEntity source);

    // FIELD_LEVEL_CUSTOMIZATIONS
    @Mapping(source = "storeId", target = "store.storeId")
    @Mapping(source = "defaultImageId", target = "defaultImage.mediaId")
    @Mapping(source = "id", target = "productId")
    Set<ProductResponse> toResponses(Set<ProductEntity> sourceSet);

    // FIELD_LEVEL_CUSTOMIZATIONS
    @Mapping(source = "storeId", target = "store.storeId")
    @Mapping(source = "defaultImageId", target = "defaultImage.mediaId")
    @Mapping(source = "id", target = "productId")
    List<ProductResponse> toResponses(List<ProductEntity> sourceList);

}
