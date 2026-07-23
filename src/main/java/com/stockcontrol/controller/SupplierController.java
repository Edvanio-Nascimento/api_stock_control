package com.stockcontrol.controller;

import com.stockcontrol.domain.dto.supplier.SupplierAudit;
import com.stockcontrol.domain.dto.supplier.SupplierRequest;
import com.stockcontrol.domain.dto.supplier.SupplierResponse;
import com.stockcontrol.domain.dto.supplier.SupplierUpdate;
import com.stockcontrol.domain.entity.Supplier;
import com.stockcontrol.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody @Valid SupplierRequest request) {

        SupplierResponse supplier = service.addSupplier(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplier.id())
                .toUri();

        return ResponseEntity.created(location).body(supplier);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierAudit> getAudit(@PathVariable UUID id) {

        return ResponseEntity.ok(service.getByAuditing(id));

    }

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false, defaultValue = "false") boolean inactivated,
                                                         @RequestParam(required = false) String cnpj) {

        return ResponseEntity.ok(service.getAllSuppliers(name, inactivated, cnpj));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable UUID id, @RequestBody @Valid SupplierUpdate update) {

        return ResponseEntity.ok(service.updateSupplier(id, update));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        service.deleteSupplier(id);

        return ResponseEntity.noContent().build();

    }

}
