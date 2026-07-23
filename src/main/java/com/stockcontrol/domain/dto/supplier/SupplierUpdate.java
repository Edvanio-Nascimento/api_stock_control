package com.stockcontrol.domain.dto.supplier;

import com.stockcontrol.domain.entity.Supplier;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record SupplierUpdate(

        String name,

        @Email
        String email

) {

    public Supplier toEntity() {
        Supplier supplier = new Supplier();

        supplier.setName(this.name);
        supplier.setEmail(this.email);

        return supplier;
    }

}
