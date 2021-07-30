package be.fooda.backend.product.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByIsActive(Boolean isActive, Pageable pageable);

    List<ProductEntity> findAllByTitleContains(String title);

    Optional<ProductEntity> findByTitleAndStoreId(String title, Long storeId);

    boolean existsByTitleAndStoreId(String title, Long storeId);

}
