package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

public class CreateProductRequest implements Serializable{

    String title;

    String eTrackingId;

    String description;

    Integer limitPerOrder = 0;

    Boolean isFeatured = Boolean.FALSE;

    String storeId;

    CreateTypeRequest type;

    @Delegate(types = PriceCollection.class)
    final Collection<CreatePriceRequest> prices = new ArrayList<>();

    @Delegate(types = TaxCollection.class)
    final Collection<CreateTaxRequest> taxes = new ArrayList<>();

    String defaultImageId;

    @Delegate(types = CategoryCollection.class)
    final Collection<CreateCategoryRequest> categories = new ArrayList<>();

    @Delegate(types = TagCollection.class)
    final Collection<CreateTagRequest> tags = new ArrayList<>();

    @Delegate(types = IngredientCollection.class)
    final Collection<CreateIngredientRequest> ingredients = new ArrayList<>();

    private interface IngredientCollection {
        boolean add(CreateIngredientRequest ingredient);
        boolean remove(CreateIngredientRequest ingredient);
    }

    private interface TagCollection {
        boolean add(CreateTagRequest tag);
        boolean remove(CreateTagRequest tag);
    }
   
    private interface TaxCollection {
        boolean add(CreateTaxRequest tax);
        boolean remove(CreateTaxRequest tax);
    }

    private interface PriceCollection {
        boolean add(CreatePriceRequest price);
        boolean remove(CreatePriceRequest price);
    }

    private interface CategoryCollection {
        boolean add(CreateCategoryRequest category);
        boolean remove(CreateCategoryRequest category);
    }

}
