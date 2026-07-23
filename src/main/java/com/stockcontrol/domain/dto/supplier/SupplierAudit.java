package com.stockcontrol.domain.dto.supplier;

import com.stockcontrol.domain.entity.Supplier;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupplierAudit(

        UUID id,

        String name,

        boolean active,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {

    public static SupplierAudit fromEntity(Supplier supplier) {
        return new SupplierAudit(
                supplier.getId(),
                supplier.getName(),
                supplier.isActive(),
                supplier.getCreatedAt(),
                supplier.getUpdatedAt()
        );
    }

}
