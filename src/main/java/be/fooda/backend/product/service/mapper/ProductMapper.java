package be.fooda.backend.product.service.mapper;


import be.fooda.backend.product.model.create.ProductCreate;
import be.fooda.backend.product.model.entity.ProductEntity;
import be.fooda.backend.product.model.update.ProductUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ProductMapper {

    ProductEntity toEntity(ProductCreate source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toEntity(ProductUpdate source, @MappingTarget ProductEntity target);


    ProductCreate toCreate(ProductEntity source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductUpdate toUpdate(ProductEntity source, @MappingTarget ProductUpdate target);


}
