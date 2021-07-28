package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "title", "storeId" })

public class UpdateProductRequest implements Serializable {

    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    String storeId;
    UpdateTypeRequest type;

    private final Set<UpdatePriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(UpdatePriceRequest price) {
        this.prices.add(price);
    }

    public void removePrice(UpdatePriceRequest price) {
        this.prices.remove(price);
    }

    private final Set<UpdateTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(UpdateTaxRequest tax) {
        this.taxes.add(tax);
    }

    public void removeTax(UpdateTaxRequest tax) {
        this.taxes.remove(tax);
    }

    String defaultImageId;

    private final Set<UpdateCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategories(UpdateCategoryRequest category){
        this.categories.add(category);
    }

    public void removeCategories(UpdateCategoryRequest category){
        this.categories.remove(category);
    }

    private final Set<UpdateTagRequest> tags = new LinkedHashSet<>();

    public void addTag(UpdateTagRequest tag){
        this.tags.add(tag);
    }

    public void removeTag(UpdateTagRequest tag){
        this.tags.remove(tag);
    }

    private final Set<UpdateIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(UpdateIngredientRequest ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(UpdateIngredientRequest ingredient){
        this.ingredients.remove(ingredient);
    }

}
