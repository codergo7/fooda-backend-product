package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "title", "storeId" })

public class CreateProductRequest implements Serializable{

    String title;

    String eTrackingId;

    String description;

    Integer limitPerOrder = 0;

    Boolean isFeatured = Boolean.FALSE;

    Long storeId;

    CreateTypeRequest type;

    private final Set<CreatePriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(CreatePriceRequest price){
        this.prices.add(price);
    }

    public void removePrice(CreatePriceRequest price){
        this.prices.remove(price);
    }

    private final Set<CreateTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(CreateTaxRequest tax){
        this.taxes.add(tax);
    }

    public void removeTax(CreateTaxRequest tax){
        this.taxes.remove(tax);
    }

    Long defaultImageId;

    private final Set<CreateCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategory(CreateCategoryRequest category){
        this.categories.add(category);
    }

    public void removeCategory(CreateCategoryRequest category){
        this.categories.remove(category);
    }

    private final Set<CreateTagRequest> tags = new LinkedHashSet<>();

    public void addTag(CreateTagRequest tag){
        this.tags.add(tag);
    }

    public void removeTag(CreateTagRequest tag){
        this.tags.remove(tag);
    }

    private final Set<CreateIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(CreateIngredientRequest ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(CreateIngredientRequest ingredient){
        this.ingredients.remove(ingredient);
    }

}
