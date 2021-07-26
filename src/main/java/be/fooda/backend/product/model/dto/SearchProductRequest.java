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
