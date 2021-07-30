package be.fooda.backend.product.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import be.fooda.backend.product.model.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {


}
