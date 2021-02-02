package be.fooda.backend.product.dao;

import be.fooda.backend.product.model.entity.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaxRepository extends JpaRepository<TaxEntity, UUID> {


    @Query("SELECT px FROM TaxEntity px WHERE px.product.id= :productId")
    List<TaxEntity> findAllByProductId(@Param("productId") UUID productId);
}