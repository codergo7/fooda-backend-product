package be.fooda.backend.product.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Delegate;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;
import java.util.*;

@Jacksonized
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchProductRequest {

    Boolean isActive;
    String name;
    String eTrackingId;
    String description;
    Integer limitPerOrder;
    Boolean isFeatured;
    UUID storeId;
    SearchTypeRequest type;

    @Delegate(types = PriceCollection.class)
    final List<SearchPriceRequest> prices = new ArrayList<>();

    @Delegate(types = TaxCollection.class)
    final List<SearchTaxRequest> taxes = new ArrayList<>();

    String defaultImageId;

    @Delegate(types = CategoryCollection.class)
    final List<SearchCategoryRequest> categories = new ArrayList<>();

    @Delegate(types = TagCollection.class)
    final List<SearchTagRequest> tags = new ArrayList<>();

    @Delegate(types = IngredientCollection.class)
    final List<SearchIngredientRequest> ingredients = new ArrayList<>();

    private interface IngredientCollection {
        boolean add(SearchIngredientRequest ingredient);

        boolean remove(SearchIngredientRequest ingredient);
    }

    private interface TagCollection {
        boolean add(SearchTagRequest tag);

        boolean remove(SearchTagRequest tag);
    }

    private interface TaxCollection {
        boolean add(SearchTaxRequest tax);

        boolean remove(SearchTaxRequest tax);
    }

    private interface PriceCollection {
        boolean add(SearchPriceRequest price);

        boolean remove(SearchPriceRequest price);
    }

    private interface CategoryCollection {
        boolean add(SearchCategoryRequest category);

        boolean remove(SearchCategoryRequest category);
    }

}
