package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

public class UpdateProductRequest implements Serializable{

    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    String storeId;
    UpdateTypeRequest type;
    
    @Delegate(types = PriceCollection.class)
    final List<UpdatePriceRequest> prices = new ArrayList<>();

    @Delegate(types = TaxCollection.class)
    final List<UpdateTaxRequest> taxes = new ArrayList<>();

    String defaultImageId;

    @Delegate(types = CategoryCollection.class)
    final List<UpdateCategoryRequest> categories = new ArrayList<>();

    @Delegate(types = TagCollection.class)
    final List<UpdateTagRequest> tags = new ArrayList<>();

    @Delegate(types = IngredientCollection.class)
    final List<UpdateIngredientRequest> ingredients = new ArrayList<>();

    private interface IngredientCollection {
        boolean add(UpdateIngredientRequest ingredient);
        boolean remove(UpdateIngredientRequest ingredient);
    }

    private interface TagCollection {
        boolean add(UpdateTagRequest tag);
        boolean remove(UpdateTagRequest tag);
    }
   
    private interface TaxCollection {
        boolean add(UpdateTaxRequest tax);
        boolean remove(UpdateTaxRequest tax);
    }

    private interface PriceCollection {
        boolean add(UpdatePriceRequest price);
        boolean remove(UpdatePriceRequest price);
    }

    private interface CategoryCollection {
        boolean add(UpdateCategoryRequest category);
        boolean remove(UpdateCategoryRequest category);
    }

}

