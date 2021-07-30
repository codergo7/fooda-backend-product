package be.fooda.backend.product.service.flow;

import be.fooda.backend.product.dao.PriceRepository;
import be.fooda.backend.product.model.dto.PriceResponse;
import be.fooda.backend.product.model.entity.PriceEntity;
import be.fooda.backend.product.service.exception.ResourceNotFoundException;
import be.fooda.backend.product.service.mapper.PriceMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    public PriceResponse findById(Long priceId) throws ResourceNotFoundException, NullPointerException, JsonProcessingException {

        // EXCEPTIONS
        if(Objects.isNull(priceId)){
            throw new NullPointerException("Price ID is required");
        }

        // FLOW
        Optional<PriceEntity> oEntity = priceRepository.findById(priceId);

        // EXCEPTION
        if(oEntity.isEmpty()){
            throw new ResourceNotFoundException("Price does NOT exist!");
        }

        PriceResponse response = priceMapper.toResponse(oEntity.get());

        // LOG
        final String NEW_LINES = "\n\n";
        log.info(NEW_LINES + "PRICE CONTROLLER -> findById(Long id) -> "
                + NEW_LINES + jsonMapper.writeValueAsString(response));

        return response;
    }

    public Set<PriceResponse> findAllByProductId(
            int pageNo, int pageSize, Long productId
    ) throws ResourceNotFoundException, NullPointerException, JsonProcessingException  {

        // EXCEPTIONS
        if(Objects.isNull(pageNo)){
            throw new NullPointerException("Page number is required");
        }

        if(Objects.isNull(pageSize)){
            throw new NullPointerException("Page size is required");
        }

        final int actualPageNumberIndex = pageNo - 1;
        Pageable pageable = PageRequest.of(actualPageNumberIndex, pageSize);
        Page<PriceEntity> entities = priceRepository.findByProductId(productId, pageable);
        Set<PriceResponse> responses = priceMapper.toResponses(entities.toSet());
        return responses;
    }

    public BigDecimal findDefaultPriceByProductId(Long productId)
            throws ResourceNotFoundException, NullPointerException, JsonProcessingException  {

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
