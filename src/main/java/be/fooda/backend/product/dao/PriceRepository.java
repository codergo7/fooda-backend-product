package be.fooda.backend.product.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import be.fooda.backend.product.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("select p from prices p where p.product.id = :productId")
    Set<PriceEntity> findByProductId(Long productId);

    @Query("select p from prices p where p.product.id = :productId")
    Page<PriceEntity> findByProductId(Long productId, Pageable pageable);

    @Query("select p from prices p where p.product.id = :productId and p.isDefault = true")
    Optional<PriceEntity> findByProductIdAndDefault(Long productId);

}
