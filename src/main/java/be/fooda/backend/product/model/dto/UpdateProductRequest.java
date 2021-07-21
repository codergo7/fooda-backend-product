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
public class UpdateProductRequest {

    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    String storeId;
    UpdateTypeRequest type;
    
    Collection<UpdatePriceRequest> prices = new ArrayList<>();

    public void addPrice(UpdatePriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(UpdatePriceRequest price) {
        this.prices.remove(price);
    }

    Collection<UpdateTaxRequest> taxes = new ArrayList<>();

    public void addTax(UpdateTaxRequest tax) {
        this.taxes.add(tax);
    }

    public void removeTax(UpdateTaxRequest tax) {
        this.taxes.remove(tax);
    }

    UpdateMediaRequest defaultImage;

    public void setDefaultImage(UpdateMediaRequest defaultImage) {
        this.defaultImage = defaultImage;
    }

    Collection<UpdateCategoryRequest> categories = new ArrayList<>();

    public void addCategory(UpdateCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(UpdateCategoryRequest category) {
        this.categories.remove(category);
    }

    Collection<UpdateTagRequest> tags = new ArrayList<>();

    public void addTag(UpdateTagRequest tag) {
        this.tags.add(tag);
    }

    public void removeTag(UpdateTagRequest tag) {
        this.tags.remove(tag);
    }

    Collection<UpdateIngredientRequest> ingredients = new ArrayList<>();

    public void addIngredient(UpdateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(UpdateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

}

