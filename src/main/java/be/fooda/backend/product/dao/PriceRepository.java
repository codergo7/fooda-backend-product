package be.fooda.backend.product.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<PriceEntity, UUID> {

    @Query("select p from prices p where p.product.id = :productId")
    List<PriceEntity> findByProductId(UUID productId);

    @Query("select p from prices p where p.product.id = :productId and p.isDefault = true")
    Optional<PriceEntity> findByProductIdAndDefault(UUID productId);

}
