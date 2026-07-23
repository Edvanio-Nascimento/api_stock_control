package com.stockcontrol.domain.dto.category;

import com.stockcontrol.domain.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryUpdate(

        @NotBlank
        @Size(min = 5, max = 100, message = "O campo NAME é obrigatório e deve conter entre 5 e 100 caracteres.")
        String name

) {

}
