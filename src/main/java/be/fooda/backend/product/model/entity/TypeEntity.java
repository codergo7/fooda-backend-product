package be.fooda.backend.product.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum TypeEntity {

    SIMPLE("A product which have no variations"), GROUPED("A product which has variations"),
    COMPLEX("multiple products in a product");

    String value;

}
