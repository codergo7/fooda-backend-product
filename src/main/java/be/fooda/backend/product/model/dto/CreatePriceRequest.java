package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "title" })

public class CreatePriceRequest implements Serializable{

    String title;
    BigDecimal amount;

}
