package com.stockcontrol.repository;

import com.stockcontrol.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findByActiveTrue();

    List<Category> findByActiveFalse();

    List<Category> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    List<Category> findByNameContainingIgnoreCaseAndActiveFalse(String name);

    boolean existsByNameIgnoreCase(String name);

}
