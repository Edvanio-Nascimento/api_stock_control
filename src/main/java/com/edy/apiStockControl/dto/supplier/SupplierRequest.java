package com.edy.apiStockControl.dto.supplier;

import com.edy.apiStockControl.model.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record SupplierRequest(

        @NotBlank(message = "Nome da empresa é obrigatório.")
        @Size(min = 6, max = 100, message = "O nome da empresa deve conter entre 6 e 100 caracteres.")
        String companyName,

        @NotBlank(message = "O cnpj é obrigatório.")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas 14 números")
        @CNPJ
        String cnpj,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "O e-mail informado é inválido.")
        @Size(max = 50, message = "O e-mail é muito longo.")
        String contactEmail

) {

    public Supplier toEntity() {
        return new Supplier(
                this.companyName().trim().toUpperCase(),
                this.cnpj().trim(),
                this.contactEmail().trim().toLowerCase()
        );
    }

}
