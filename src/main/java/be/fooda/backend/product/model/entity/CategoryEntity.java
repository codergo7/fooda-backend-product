package be.fooda.backend.product.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.springframework.data.domain.Persistable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

// LOMBOK
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })

// JPA
@Entity(name = "categories")

public class CategoryEntity implements Serializable, Persistable<UUID> {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    UUID id;

    @FullTextField
    @Column(nullable = false, unique = false)
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
