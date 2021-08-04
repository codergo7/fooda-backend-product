package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "ingredientId" })

// JACKSON
@JsonIgnoreProperties(ignoreUnknown = true)

public class IngredientResponse implements Serializable{

    Long ingredientId;
    String title;
    BigDecimal price;

}
