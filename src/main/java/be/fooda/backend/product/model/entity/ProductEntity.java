package be.fooda.backend.product.model.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})

// JPA
@Entity

// HIBERNATE SEARCH
@Indexed

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @KeywordField
    UUID id;

    Boolean isActive = Boolean.TRUE;

    @FullTextField
    String title;

    @KeywordField
    @Column(unique = true)
    String eTrackingId;

    @Lob
    @FullTextField
    String description;

    @GenericField
    Integer limitPerOrder = 0;

    Boolean isFeatured = Boolean.FALSE;

    String storeId;

    @FullTextField
    @Enumerated(EnumType.STRING)
    TypeEntity type;
    
    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PriceEntity> prices;

    public void setPrices(List<PriceEntity> prices) {
        for (int index = 0; index < prices.size(); index++) {
            prices.get(index).setProduct(this);
        }
        this.prices = prices;
    }

    public void addPrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.add(price);
    }

    public void removePrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TaxEntity> taxes;

    public void addTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(List<TaxEntity> taxes) {
        for (int index = 0; index < taxes.size(); index++) {
            taxes.get(index).setProduct(this);
        }

        this.taxes = taxes;
    }

    String defaultImageId;

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CategoryEntity> categories;

    public void addCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(List<CategoryEntity> categories) {
        for(int index = 0; index < categories.size(); index++){
            categories.get(index).setProduct(this);
        }
        this.categories = categories;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TagEntity> tags;

    public void addTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(List<TagEntity> tags) {
        for(int index = 0; index < tags.size(); index++){
            tags.get(index).setProduct(this);
        }

        this.tags = tags;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    List<IngredientEntity> ingredients;

    public void addIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.remove(ingredient);
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        for(int index = 0; index < ingredients.size(); index++){
            ingredients.get(index).setProduct(this);
        }

        this.ingredients = ingredients;
    }
}
