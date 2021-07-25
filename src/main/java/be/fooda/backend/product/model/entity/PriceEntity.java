package be.fooda.backend.product.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

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
@Entity

public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne(fetch = FetchType.LAZY)
    ProductEntity product;
}
