package be.fooda.backend.product.service.mapper;

import be.fooda.backend.product.model.dto.CreatePriceRequest;
import be.fooda.backend.product.model.dto.PriceResponse;
import be.fooda.backend.product.model.dto.UpdatePriceRequest;
import be.fooda.backend.product.model.entity.PriceEntity;
import org.mapstruct.*;

import java.util.Set;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PriceMapper {

    PriceEntity toEntity(CreatePriceRequest source);

    PriceEntity toEntity(UpdatePriceRequest source, @MappingTarget PriceEntity target);

    CreatePriceRequest toCreate(PriceEntity source);

    UpdatePriceRequest toUpdate(PriceEntity source, @MappingTarget UpdatePriceRequest target);

    @Mapping(source = "id", target = "priceId")
    PriceResponse toResponse(PriceEntity source);

    @Mapping(source = "id", target = "priceId")
    Set<PriceResponse> toResponses(Set<PriceEntity> sources);

}
