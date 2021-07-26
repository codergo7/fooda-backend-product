package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "title", "amount" })

public class UpdatePriceRequest implements Serializable{

    String title;
    BigDecimal amount;
    LocalDateTime expiresAt;
    Boolean isDefault;
    String currency;

}
