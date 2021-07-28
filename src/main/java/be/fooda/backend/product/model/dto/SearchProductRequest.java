package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

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

public class SearchProductRequest implements Serializable{

    Boolean isActive;
    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    UUID storeId;
    SearchTypeRequest type;

    private final Set<SearchPriceRequest> prices = new LinkedHashSet<>();

    public void addPrice(SearchPriceRequest price){
        this.prices.add(price);
    }

    public void removePrice(SearchPriceRequest price){
        this.prices.remove(price);
    }

    private final Set<SearchTaxRequest> taxes = new LinkedHashSet<>();

    public void addTax(SearchTaxRequest tax){
        this.taxes.add(tax);
    }

    public void removeTax(SearchTaxRequest tax){
        this.taxes.remove(tax);
    }

    String defaultImageId;

    private final Set<SearchCategoryRequest> categories = new LinkedHashSet<>();

    public void addCategory(SearchCategoryRequest category){
        this.categories.add(category);
    }

    public void removeCategory(SearchCategoryRequest category){
        this.categories.remove(category);
    }

    private final Set<SearchTagRequest> tags = new LinkedHashSet<>();

    public void addTag(SearchTagRequest tag){
        this.tags.add(tag);
    }

    public void removeTag(SearchTagRequest tag){
        this.tags.remove(tag);
    }

    private final Set<SearchIngredientRequest> ingredients = new LinkedHashSet<>();

    public void addIngredient(SearchIngredientRequest ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(SearchIngredientRequest ingredient){
        this.ingredients.remove(ingredient);
    }

}
