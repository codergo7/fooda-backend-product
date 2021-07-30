package be.fooda.backend.product.model.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.springframework.data.domain.Persistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })

// JPA
@Entity(name = "products")

// HIBERNATE SEARCH
@Indexed

public class ProductEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long id;

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

    Long storeId;

    @FullTextField
    @Enumerated(EnumType.STRING)
    TypeEntity type;

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PriceEntity> prices = new LinkedHashSet<>();

    public void setPrices(Set<PriceEntity> prices) {
        this.prices = prices;
        for (PriceEntity price : this.prices) {
            price.setProduct(this);
        }
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
    private Set<TaxEntity> taxes = new LinkedHashSet<>();

    public void addTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(Set<TaxEntity> taxes) {
        this.taxes = taxes;
        for(TaxEntity tax : taxes){
            tax.setProduct(this);
        }
    }

    Long defaultImageId;

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CategoryEntity> categories = new LinkedHashSet<>();

    public void addCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        for(CategoryEntity category : this.categories){
            category.setProduct(this);
        }
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TagEntity> tags = new LinkedHashSet<>();

    public void addTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(Set<TagEntity> tags) {
        this.tags = tags;
        for(TagEntity tag : tags){
            tag.setProduct(this);
        }
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IngredientEntity> ingredients = new LinkedHashSet<>();

    public void addIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.remove(ingredient);
    }

    public void setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        for(IngredientEntity ingredient : ingredients){
            ingredient.setProduct(this);
        }
    }

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
