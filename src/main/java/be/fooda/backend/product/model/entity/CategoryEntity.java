package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

// LOMBOK
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)

// JPA
@Entity

public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @FullTextField
    @Column(nullable = false, unique = false)
    String title;

    @Lob
    Byte[] icon;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryEntity)) {
            return false;
        }
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"CategoryEntity\":{"
                + "                        \"categoryId\":" + getId()
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"productId\":" + product.getId()
                + "}}";
    }
}
