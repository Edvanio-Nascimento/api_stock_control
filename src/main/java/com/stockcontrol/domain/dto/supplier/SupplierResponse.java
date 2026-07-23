package com.stockcontrol.domain.dto.supplier;

import com.stockcontrol.domain.entity.Supplier;

import java.util.UUID;

public record SupplierResponse(

        UUID id,

        String name,

        String cnpj,

        String email,

        boolean active

) {

    public static SupplierResponse fromEntity(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getName(),
                supplier.getCnpj(),
                supplier.getEmail(),
                supplier.isActive()
        );
    }

}
