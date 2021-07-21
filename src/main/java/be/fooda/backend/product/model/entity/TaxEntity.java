package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.validator.constraints.Range;

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

public class TaxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @Column(nullable = false, unique = false)
    @FullTextField
    String title;

    @GenericField
    @Range(min = 0, max = 100)
    Double percentage = 0.00;

    Boolean isDefault = Boolean.FALSE;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxEntity)) {
            return false;
        }
        final var taxEntity = (TaxEntity) o;
        return Objects.equals(getId(), taxEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"TaxEntity\":{"
                + "                        \"id\":" + getId()
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"percentage\":\"" + percentage + "\""
                + ",                         \"isDefault\":\"" + isDefault + "\""
                + ",                         \"product\":" + product.getId()
                + "}}";
    }
}
