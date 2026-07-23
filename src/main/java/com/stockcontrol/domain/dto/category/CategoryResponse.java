package com.stockcontrol.domain.dto.category;

import com.stockcontrol.domain.entity.Category;

import java.util.UUID;

public record CategoryResponse(

        UUID id,

        String name,

        boolean active

) {

    public static CategoryResponse fromEntity(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.isActive()
        );
    }

}
