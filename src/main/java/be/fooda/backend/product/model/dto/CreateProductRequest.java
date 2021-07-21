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
public class CreateProductRequest {

    // UNIQUE: title + eStoreId
    
    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    String storeId;
    CreateTypeRequest type;

    Collection<CreatePriceRequest> prices = new ArrayList<>();

    public void addPrice(CreatePriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(CreatePriceRequest price) {
        this.prices.remove(price);
    }

    Collection<CreateTaxRequest> taxes = new ArrayList<>();

    public void addTax(CreateTaxRequest tax) {
        this.taxes.add(tax);
    }

    public void removeTax(CreateTaxRequest tax) {
        this.taxes.remove(tax);
    }

    String defaultImageId;

    Collection<CreateCategoryRequest> categories = new ArrayList<>();

    public void addCategory(CreateCategoryRequest category) {
        this.categories.add(category);
    }

    public void removeCategory(CreateCategoryRequest category) {
        this.categories.remove(category);
    }

    Collection<CreateTagRequest> tags = new ArrayList<>();

    public void addTag(CreateTagRequest tag) {
        this.tags.add(tag);
    }

    public void removeTag(CreateTagRequest tag) {
        this.tags.remove(tag);
    }

    Collection<CreateIngredientRequest> ingredients = new ArrayList<>();

    public void addIngredient(CreateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(CreateIngredientRequest ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductRequest)) return false;
        CreateProductRequest that = (CreateProductRequest) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getStoreId(), that.getStoreId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getStoreId());
    }
}

