package be.fooda.backend.product.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpSuccessMassages {
    PRODUCT_CREATED("Product is created based on the given information"),
    PRODUCT_UPDATED("Product successfully updated"),
    PRODUCT_DELETED("Product deleted based on the new information"),
    PRODUCT_MADE_PASSIVE("Product is made passive."),
    PRODUCT_LIST_CREATED("All Products from the list are created.. "),
    PRODUCT_CATEGORY_ADDED("Category is added to the product"),
    PRODUCT_EXISTS("Product already exists."),
    CATEGORY_DELETED("category is deleted"),
    INGREDIENT_ADDED("ingredient is added"),
    TAG_ADDED("tag is added"),
    INGREDIENT_DELETED("ingredient is deleted"),
    PRODUCT_INGREDIENT_ADDED("ingredient is added to product"),
    PRODUCT_TAG_ADDED("tag is added to product"),
    TAG_DELETED("tag is deleted"),
    PRODUCT_PRICE_ADDED("price added to th product"),
    TAX_DELETED("tax deleted"), PRODUCT_TAX_ADDED("tax added to product");

    private final String description;
}
