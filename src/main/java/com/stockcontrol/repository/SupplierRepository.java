package com.stockcontrol.repository;

import com.stockcontrol.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    List<Supplier> findByActiveTrue();

    List<Supplier> findByActiveFalse();

    List<Supplier> findByNameContainingIgnoreCaseAndActiveFalse(String name);

    List<Supplier> findByNameContainingIgnoreCaseAndActiveTrue(String name);

    List<Supplier> findByCnpjContaining(String cnpj);

    boolean existsByCnpj(String cnpj);

}
