package com.edy.apiStockControl.dto.supplier;

import com.edy.apiStockControl.model.Supplier;

import java.util.UUID;

public record SupplierResponse(

        UUID id,
        String companyName,
        String cnpj,
        String contactEmail

) {

    public static SupplierResponse fromEntity(Supplier supplier) {

        if (supplier == null) return null;

        return new SupplierResponse(
                supplier.getId(),
                supplier.getCompanyName(),
                formatCnpj(supplier.getCnpj()),
                supplier.getContactEmail()
        );
    }

    private static String formatCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14) return cnpj;
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

}
