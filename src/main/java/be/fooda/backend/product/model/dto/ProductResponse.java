package be.fooda.backend.product.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
@EqualsAndHashCode(of = { "productId" })

public class ProductResponse implements Serializable {

    UUID productId;
    Boolean isActive;
    String title;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;

    StoreResponse store;
    
    TypeResponse type;

    @Delegate(types = PriceCollection.class)
    private final List<PriceResponse> prices = new ArrayList<>();

    @Delegate(types = TaxCollection.class)
    private final List<TaxResponse> taxes = new ArrayList<>();

    MediaResponse defaultImage;

    @Delegate(types = CategoryCollection.class)
    private final List<CategoryResponse> categories = new ArrayList<>();

    @Delegate(types = TagCollection.class)
    private final List<TagResponse> tags = new ArrayList<>();

    @Delegate(types = IngredientCollection.class)
    private final List<IngredientResponse> ingredients = new ArrayList<>();

    private interface IngredientCollection {
        boolean add(IngredientResponse ingredient);

        boolean remove(IngredientResponse ingredient);
    }

    private interface TagCollection {
        boolean add(TagResponse tag);

        boolean remove(TagResponse tag);
    }

    private interface TaxCollection {
        boolean add(TaxResponse tax);

        boolean remove(TaxResponse tax);
    }

    private interface PriceCollection {
        boolean add(PriceResponse price);

        boolean remove(PriceResponse price);
    }

    private interface CategoryCollection {
        boolean add(CategoryResponse category);

        boolean remove(CategoryResponse category);
    }

}
