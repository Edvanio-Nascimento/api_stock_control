package com.stockcontrol.domain.dto.supplier;

import com.stockcontrol.domain.entity.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record SupplierRequest(

        @NotBlank
        @Size(min = 4, max = 100, message = "O campo NAME é obrigatório e deve conter entre 4 e 100 caracteres.")
        String name,

        @NotBlank
        @Size(min = 14, max = 14, message = "O campo CNPJ é obrigatório e deve conter exatamente 14 caracteres.")
        @CNPJ
        String cnpj,

        @NotBlank
        @Size(min = 5, max = 100, message = "O campo EMAIL é obrigatório e deve conter entre 5 e 100 caracteres.")
        @Email
        String email

) {

    public Supplier toEntity() {
        Supplier supplier = new Supplier();

        supplier.setName(this.name);
        supplier.setCnpj(this.cnpj);
        supplier.setEmail(this.email);

        return supplier;
    }

}
