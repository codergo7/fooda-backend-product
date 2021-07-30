package be.fooda.backend.product.service.flow;

import be.fooda.backend.product.dao.PriceRepository;
import be.fooda.backend.product.dao.ProductIndexer;
import be.fooda.backend.product.dao.ProductRepository;
import be.fooda.backend.product.model.entity.PriceEntity;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.mapper.PriceMapper;
import be.fooda.backend.product.service.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

// LOMBOK
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

// SPRING
@Service
public class PriceFlow {

    PriceRepository priceRepository;
    PriceMapper priceMapper;
    ObjectMapper jsonMapper;

    public BigDecimal findDefaultPriceByProductId(UUID productId)
            throws ResourceNotFoundException, NullPointerException {

        Optional<PriceEntity> oEntity = priceRepository.findByProductIdAndDefault(productId);

        if(Objects.isNull(productId)){
            throw new NullPointerException("Product ID is required");
        }

        if(oEntity.isEmpty()){
            throw new ResourceNotFoundException("Price NOT found");
        }

        PriceEntity entity = oEntity.get();
        return entity.getAmount();
    }
}
