package be.fooda.backend.product.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpFailureMassages {
    USER_DOES_NOT_EXIST("There is no such user with the given information"),
    STORE_DOES_NOT_EXIST("There is no such store with the given information"),
    PRODUCT_CREATE_ERROR("There was an error while PRODUCT is being created.."),
    PRODUCT_ALREADY_EXIST("The PRODUCT already exists.."),
    PROBLEM_WITH_CREATING_IN_DB("There has been a problem while creating the PRODUCT in the database.."),
    MEDIA_DOES_NOT_EXIST("MEDIA does not exist."),
    NO_PRODUCTS_FOUND("Could not found product with the given information"),
    UNKNOWN_FAILURE("  An unknown exception happened."),
    NO_PRODUCTS_IS_FEATURED("no product is featured"),
    FAILED_TO_CREATE_PRODUCT("There has been a problem while creating the PRODUCT"),
    PRODUCT_DOES_NOT_EXIST("There is no such product  "),
    PRODUCT_COULD_NOT_BE_DELETED("there some error in deleting the product"),
    SOME_PRODUCTS_EXIST_FROM_THE_LIST("Some of the products exist in the list."),
    PRODUCT_NOT_FOUND("product could not found"),
    NO_FIELD_IS_SENT_TO_UPDATE_REQUEST("...... "),
    NO_STORE_FOUND("store not found"),
    NO_CATEGORIES_FOUND(" category is not found"),
    NO_INGREDIENTS_FOUND("ingredients not found"),
    NO_TAGS_FOUND("no tags is found"),
    CATEGORY_NOT_FOUND("category not found"),
    CATEGORY_COULD_NOT_BE_DELETED("category can not be deleted"),
    CATEGORY_DOES_NOT_EXIST("category do not exist"),
    INGREDIENT_ALREADY_EXIST("ingredient is not exist"),
    TAG_ALREADY_EXIST("tag already is added"),
    INGREDIENT_DOES_NOT_EXIST("ingredient does not exist"),
    INGREDIENT_COULD_NOT_BE_DELETED("ingredient can not be deleted"),
    TAG_DOES_NOT_EXIST("tag does not exist"),
    NO_PRODUCT_PRICE_IS_FOUND("no price is found for this product"), TAX_DOES_NOT_EXIST(""),
    FAILED_TO_UPDATE_PRODUCT("There has been a problem while updating the PRODUCT"), 
    PRODUCT_ID_IS_REQUIRED("Product ID is required"), 
    PRODUCT_TITLE_IS_REQUIRED("Product title is required"), 
    STORE_ID_IS_REQUIRED("Store ID is required"),
    PRODUCT_IS_REQUIRED("Product information is required"),
    FAILED_TO_MAKE_PRODUCT_PASSIVE("Failed to make product passive"),
    FAILED_TO_DELETE_PRODUCT_PERMANENTLY("Failed to delete product permanently");

    private final String description;
}
