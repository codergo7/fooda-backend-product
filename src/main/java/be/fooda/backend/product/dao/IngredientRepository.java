package be.fooda.backend.product.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.IngredientEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {


}
