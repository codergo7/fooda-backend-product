package be.fooda.backend.product.model.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
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
@Entity(name = "tags")

public class TagEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue
    Long id;

    @FullTextField
    @Column(nullable = false, unique = false)
    String value;

    @ToString.Exclude
    @JoinColumn(name = "product_id")
    @ManyToOne
    ProductEntity product;

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
