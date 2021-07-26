package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "categoryId" })

public class CategoryResponse implements Serializable {

    UUID categoryId;
    String title;
    Byte[] icon;
}
