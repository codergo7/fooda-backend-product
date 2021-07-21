package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

public class MediaEntity {

    @Id
    @Column(nullable = false, unique = true)
    UUID id;

    @FullTextField
    @URL
    @Column(nullable = false, unique = true)
    String url;

    Boolean isDefault = Boolean.FALSE;

    @JoinColumn(name = "product_id")
    @OneToOne
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediaEntity)) {
            return false;
        }
        MediaEntity that = (MediaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "{\"MediaEntity\":{"
                + "                        \"id\":" + getId()
                + ",                         \"url\":\"" + url + "\""
                + ",                         \"isDefault\":\"" + isDefault + "\""
                + ",                         \"productId\":" + product.getId()
                + "}}";
    }
}
