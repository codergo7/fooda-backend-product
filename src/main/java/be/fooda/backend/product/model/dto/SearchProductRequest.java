package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.*;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchProductRequest {

    Boolean isActive;
    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    UUID storeId;
    SearchTypeRequest type;

    Collection<SearchPriceRequest> prices;

    public void addPrice(SearchPriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(SearchPriceRequest price) {
        this.prices.remove(price);
    }

    Collection<SearchTaxRequest> taxes;

    public void addTax(SearchTaxRequest tax) {
        this.taxes.add(tax);
    }

    public void removeTax(SearchTaxRequest tax) {
        this.taxes.remove(tax);
    }

    SearchMediaRequest defaultImage;

    public void setDefaultImage(SearchMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    Collection<SearchCategoryRequest> categories;

    public void addCategory(SearchCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(SearchCategoryRequest category) {
        this.categories.remove(category);
    }

    Collection<SearchTagRequest> tags;

    public void addTag(SearchTagRequest tag) {
        this.tags.add(tag);
    }

    public void removeTag(SearchTagRequest tag) {
        this.tags.remove(tag);
    }

    Collection<SearchIngredientRequest> ingredients;

    public void addIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(SearchIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

}

