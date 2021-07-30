package be.fooda.backend.product.model.remote;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"priceId"})

public class ProductClientPriceResponse {
    Long productId;
    Long priceId;
    BigDecimal amount;
    String title;
}
