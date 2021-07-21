package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.util.*;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    UUID productId;
    Boolean isActive;
    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    String storeId;
    TypeResponse type;

    Collection<PriceResponse> prices = new ArrayList<>();
    
    public void addPrice(PriceResponse price) {
        this.prices.add(price);
    }

    public void removePrice(PriceResponse price) {
        this.prices.remove(price);
    }

    Collection<TaxResponse> taxes = new ArrayList<>();

    public void addTax(TaxResponse tax) {
        this.taxes.add(tax);
    }

    public void removeTax(TaxResponse tax) {
        this.taxes.remove(tax);
    }

    String defaultImageId;

    Collection<CategoryResponse> categories = new ArrayList<>();

    public void addCategory(CategoryResponse category) {
        this.categories.add(category);
    }

    public void removeCategory(CategoryResponse category) {
        this.categories.remove(category);
    }

    Collection<TagResponse> tags = new ArrayList<>();

    public void addTag(TagResponse tag) {
        this.tags.add(tag);
    }

    public void removeTag(TagResponse tag) {
        this.tags.remove(tag);
    }

    Collection<IngredientResponse> ingredients = new ArrayList<>();

    public void addIngredient(IngredientResponse ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientResponse ingredient) {
        this.ingredients.add(ingredient);
    }

}

