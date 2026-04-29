package com.edy.apiStockControl.service;

import com.edy.apiStockControl.dto.supplier.SupplierRequest;
import com.edy.apiStockControl.dto.supplier.SupplierResponse;
import com.edy.apiStockControl.dto.supplier.SupplierUpdate;
import com.edy.apiStockControl.model.Supplier;
import com.edy.apiStockControl.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public SupplierResponse createSupplier(SupplierRequest request) {
//        if (supplierRepository.existsByCnpj(request.cnpj())) {
//            throw new DuplicateKeyException("CNPJ já cadastrado para outro fornecedor.");
//        }

        supplierRepository.findByCnpj(request.cnpj())
                .ifPresent(s -> {
                    throw new DuplicateKeyException("CNPJ já cadastrado para outro fornecedor.");
                });

        Supplier supplier = request.toEntity();
        Supplier saved = supplierRepository.save(supplier);

        return SupplierResponse.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(SupplierResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public SupplierResponse getByCnpjSupplier(String cnpj) {
        Supplier supplier = supplierRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("CNPJ não encontrado."));

        return SupplierResponse.fromEntity(supplier);
    }

    @Transactional
    public SupplierResponse updateSupplier(UUID id, SupplierUpdate update) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado."));

       supplier.setCompanyName(update.companyName().trim().toUpperCase());
       supplier.setContactEmail(update.contactEmail().trim().toLowerCase());

       return SupplierResponse.fromEntity(supplierRepository.save(supplier));
    }

}
