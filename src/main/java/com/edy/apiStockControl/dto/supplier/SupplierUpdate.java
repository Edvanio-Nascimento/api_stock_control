package com.edy.apiStockControl.dto.supplier;

import com.edy.apiStockControl.model.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierUpdate(

        @NotBlank(message = "Nome da empresa é obrigatório.")
        @Size(min = 6, max = 100, message = "O nome da empresa deve conter entre 6 e 100 caracteres.")
        String companyName,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail informado é inválido.")
        @Size(max = 50, message = "O e-mail é muito longo.")
        String contactEmail

) {

    public static SupplierUpdate fromEntity(Supplier supplier) {
        return new SupplierUpdate(
                supplier.getCompanyName(),
                supplier.getContactEmail()
        );
    }

}
