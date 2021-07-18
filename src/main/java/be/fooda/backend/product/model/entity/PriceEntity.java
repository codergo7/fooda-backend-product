package be.fooda.backend.product.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID priceId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceEntity)) {
            return false;
        }
        PriceEntity that = (PriceEntity) o;
        return Objects.equals(getPriceId(), that.getPriceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriceId());
    }

    @Override
    public String toString() {
        return "{\"PriceEntity\":{"
                + "                        \"priceId\":" + priceId
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"amount\":\"" + amount + "\""
                + ",                         \"expiresAt\":" + expiresAt
                + ",                         \"isDefault\":\"" + isDefault + "\""
                + ",                         \"currency\":\"" + currency + "\""
                + ",                         \"product\":" + product.getProductId()
                + "}}";
    }
}
