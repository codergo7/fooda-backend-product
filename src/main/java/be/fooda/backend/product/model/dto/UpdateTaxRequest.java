package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.*;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "title" })

public class UpdateTaxRequest implements Serializable{

    String title;
    Double percentage;
    Boolean isDefault;

}
