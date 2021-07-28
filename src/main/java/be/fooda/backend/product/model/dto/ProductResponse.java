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

    private final List<PriceResponse> prices = new ArrayList<>();

    public void addPrice(PriceResponse price) {

    }

    public void removePrice(){
        
    }

    private final List<TaxResponse> taxes = new ArrayList<>();

    MediaResponse defaultImage;

    private final List<CategoryResponse> categories = new ArrayList<>();

    private final List<TagResponse> tags = new ArrayList<>();

    private final List<IngredientResponse> ingredients = new ArrayList<>();


}
