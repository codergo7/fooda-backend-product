package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByIsActive(Boolean isActive, Pageable pageable);

    List<ProductEntity> findAllByTitleContains(String title);

    Optional<ProductEntity> findByTitleAndStoreId(String title, Long storeId);

    boolean existsByTitleAndStoreId(String title, Long storeId);

    @Modifying
    @Query("update products p set p.isActive = false where p.id = :productId")
    int makePassive(@Param("productId") Long productId);

    boolean existsByIdAndIsActive(Long id, boolean isActive);

}
