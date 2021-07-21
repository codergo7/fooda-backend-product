package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

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
    Collection<PriceEntity> prices = new ArrayList<>();

    public void setPrices(Collection<PriceEntity> prices) {
        this.prices = prices.stream().map(this::setOnePrice).collect(Collectors.toList());
    }

    public void addPrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.add(price);
    }

    public void removePrice(PriceEntity price) {
        price.setProduct(this);
        this.prices.remove(price);
    }

    PriceEntity setOnePrice(PriceEntity price) {
        price.setProduct(this);
        return price;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<TaxEntity> taxes = new ArrayList<>();

    public void addTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.add(tax);
    }

    public void removeTax(TaxEntity tax) {
        tax.setProduct(this);
        this.taxes.remove(tax);
    }

    public void setTaxes(Collection<TaxEntity> taxes) {
        this.taxes = taxes.stream().map(this::setOneTax).collect(Collectors.toList());
    }

    TaxEntity setOneTax(TaxEntity tax) {
        tax.setProduct(this);
        return tax;
    }

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @IndexedEmbedded
    MediaEntity defaultImage;

    public void setDefaultImage(MediaEntity defaultImage) {
        defaultImage.setProduct(this);
        this.defaultImage = defaultImage;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<CategoryEntity> categories = new ArrayList<>();

    public void addCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.add(category);
    }

    public void removeCategory(CategoryEntity category) {
        category.setProduct(this);
        this.categories.remove(category);
    }

    public void setCategories(Collection<CategoryEntity> categories) {
        this.categories = categories.stream().map(this::setOneCategory).collect(Collectors.toList());
    }

    CategoryEntity setOneCategory(CategoryEntity category) {
        category.setProduct(this);
        return category;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<TagEntity> tags = new ArrayList<>();

    public void addTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.add(tag);
    }

    public void removeTag(TagEntity tag) {
        tag.setProduct(this);
        this.tags.remove(tag);
    }

    public void setTags(Collection<TagEntity> tags) {
        this.tags = tags.stream().map(this::setOneTag).collect(Collectors.toList());

    }

    TagEntity setOneTag(TagEntity tag) {
        tag.setProduct(this);
        return tag;
    }

    @IndexedEmbedded
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Collection<IngredientEntity> ingredients = new ArrayList<>();

    public void addIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        this.ingredients.add(ingredient);
    }

    public void setIngredients(Collection<IngredientEntity> ingredients) {
        this.ingredients = ingredients.stream().map(this::setOneIngredient).collect(Collectors.toList());
    }

    IngredientEntity setOneIngredient(IngredientEntity ingredient) {
        ingredient.setProduct(this);
        return ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"ProductEntity\":{" + "                        \"id\":" + getId()
                + ",                         \"isActive\":\"" + isActive + "\""
                + ",                         \"title\":\"" + getTitle() + "\"" + ",                         \"eTrackingId\":\""
                + eTrackingId + "\"" + ",                         \"description\":\"" + description + "\""
                + ",                         \"limitPerOrder\":\"" + limitPerOrder + "\""
                + ",                         \"isFeatured\":\"" + isFeatured + "\""
                + ",                         \"storeId\":" + getStoreId() + ",                         \"type\":\"" + type + "\""
                + ",                         \"prices\":" + prices + ",                         \"taxes\":" + taxes
                + ",                         \"defaultImage\":" + defaultImage
                + ",                         \"categories\":" + categories + ",                         \"tags\":"
                + tags + ",                         \"ingredients\":" + ingredients + "}}";
    }
}
