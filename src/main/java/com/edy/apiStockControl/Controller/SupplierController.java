package com.edy.apiStockControl.Controller;

import com.edy.apiStockControl.dto.supplier.SupplierRequest;
import com.edy.apiStockControl.dto.supplier.SupplierResponse;
import com.edy.apiStockControl.dto.supplier.SupplierUpdate;
import com.edy.apiStockControl.model.Supplier;
import com.edy.apiStockControl.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody @Valid SupplierRequest request) {
        SupplierResponse supplier = supplierService.createSupplier(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplier.cnpj())
                .toUri();

        return ResponseEntity.created(location).body(supplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<SupplierResponse> getByCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(supplierService.getByCnpjSupplier(cnpj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable UUID id,
                                                   @RequestBody @Valid SupplierUpdate update) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, update));
    }
}
