package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.Delegate;
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

    @Delegate(types = PriceCollection.class)
    final List<PriceResponse> prices = new ArrayList<>();

    @Delegate(types = TaxCollection.class)
    final List<TaxResponse> taxes = new ArrayList<>();

    String defaultImageId;

    @Delegate(types = CategoryCollection.class)
    final List<CategoryResponse> categories = new ArrayList<>();

    @Delegate(types = TagCollection.class)
    final List<TagResponse> tags = new ArrayList<>();

    @Delegate(types = IngredientCollection.class)
    final List<IngredientResponse> ingredients = new ArrayList<>();

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
