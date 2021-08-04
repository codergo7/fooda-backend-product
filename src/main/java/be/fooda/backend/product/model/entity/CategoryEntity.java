package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })

// JPA
@Entity(name = "categories")

public class CategoryEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long id;

    @FullTextField
    @Column(nullable = false)
    String title;

    @Lob
    Byte[] icon;

    @ToString.Exclude
    @JoinColumn(name = "product_id")
    @ManyToOne
    ProductEntity product;

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }

}
