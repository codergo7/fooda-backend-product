package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

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

public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @KeywordField
    UUID id;

    @FullTextField
    @Column(nullable = false, unique = false)
    String value;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagEntity)) {
            return false;
        }
        final var tagEntity = (TagEntity) o;
        return Objects.equals(getId(), tagEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"TagEntity\":{"
                + "                        \"id\":" + getId()
                + ",                         \"value\":\"" + value + "\""
                + ",                         \"product\":" + product.getId()
                + "}}";
    }
}
