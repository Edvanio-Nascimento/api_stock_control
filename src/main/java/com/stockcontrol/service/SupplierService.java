package com.stockcontrol.service;

import com.stockcontrol.domain.dto.supplier.SupplierAudit;
import com.stockcontrol.domain.dto.supplier.SupplierRequest;
import com.stockcontrol.domain.dto.supplier.SupplierResponse;
import com.stockcontrol.domain.dto.supplier.SupplierUpdate;
import com.stockcontrol.domain.entity.Supplier;
import com.stockcontrol.repository.SupplierRepository;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SupplierResponse addSupplier(SupplierRequest request) {

        if (repository.existsByCnpj(request.cnpj())) {
            throw new RuntimeException("CNPJ já cadastrado.");
        }

        Supplier supplier = request.toEntity();

        repository.save(supplier);

        return SupplierResponse.fromEntity(supplier);

    }

    @Transactional(readOnly = true)
    public SupplierAudit getByAuditing(UUID id) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum fornecedor encontrado com esse id: " + id));

        return SupplierAudit.fromEntity(supplier);

    }

    @Transactional(readOnly = true)
    public List<SupplierResponse> getAllSuppliers(String name, boolean inactivated, String cnpj) {

        List<Supplier> suppliers;

        if (StringUtils.hasText(cnpj)) {
            suppliers = repository.findByCnpjContaining(cnpj);
        } else if (StringUtils.hasText(name)) {
            suppliers = inactivated
                    ? repository.findByNameContainingIgnoreCaseAndActiveFalse(name)
                    : repository.findByNameContainingIgnoreCaseAndActiveTrue(name);
        } else {
            suppliers = inactivated
                    ? repository.findByActiveFalse()
                    : repository.findByActiveTrue();
        }

        return suppliers.stream().map(SupplierResponse::fromEntity).toList();

    }

    @Transactional
    public SupplierResponse updateSupplier(UUID id, SupplierUpdate update) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum fornecedor encontrado com esse id: " + id));

        if (!supplier.isActive()) {
            throw new RuntimeException("Você não pode atualizar um fornecedor inativo.");
        }

        if (update.name() != null && !update.name().trim().isEmpty()) {
            supplier.setName(update.name());
        }

        if (update.email() != null && !update.email().trim().isEmpty()) {
            supplier.setEmail(update.email());
        }

        return SupplierResponse.fromEntity(repository.save(supplier));

    }

    @Transactional
    public void deleteSupplier(UUID id) {

        Supplier supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum fornecedor encontrado com esse id: " + id));

        supplier.setActive(false);

    }
}
