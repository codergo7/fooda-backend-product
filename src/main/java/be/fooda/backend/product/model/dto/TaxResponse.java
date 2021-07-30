package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "taxId" })

public class TaxResponse implements Serializable{

    Long taxId;
    String title;
    Double percentage;
    Boolean isDefault;

}
