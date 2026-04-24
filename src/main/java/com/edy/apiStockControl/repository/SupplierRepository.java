package com.edy.apiStockControl.repository;

import com.edy.apiStockControl.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    boolean existsByCnpj(String cnpj);

    Optional<Supplier> findByCompanyNameContainingIgnoreCase(String companyName);
}
