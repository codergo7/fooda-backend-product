package be.fooda.backend.product.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
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
@EqualsAndHashCode(of = {"id"})

// JPA
@Entity(name = "prices")
@Table

public class PriceEntity implements Serializable, Persistable<UUID>  {

    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    UUID id;

    @FullTextField
    String title;

    @GenericField
    @PositiveOrZero
    BigDecimal amount = BigDecimal.ZERO;

    @GenericField
    @FutureOrPresent
    LocalDateTime expiresAt;

    Boolean isDefault = Boolean.FALSE;

    @FullTextField
    String currency = "EUR"; // EURO, €, EUR -> EUR

    @ToString.Exclude
    @JoinColumn(name = "product_id")
    @ManyToOne
    ProductEntity product;

    @Override
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
