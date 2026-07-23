package com.stockcontrol.service;

import com.stockcontrol.domain.dto.category.CategoryRequest;
import com.stockcontrol.domain.dto.category.CategoryResponse;
import com.stockcontrol.domain.dto.category.CategoryUpdate;
import com.stockcontrol.domain.entity.Category;
import com.stockcontrol.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CategoryResponse addCategory(CategoryRequest request) {

        if (repository.existsByNameIgnoreCase(request.name())) {
            throw new RuntimeException("Categoria já cadastrada.");
        }

        Category category = request.toEntity();

        repository.save(category);

        return CategoryResponse.fromEntity(category);

    }

    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(UUID id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma categoria cadastrada com esse id: " + id));

        return CategoryResponse.fromEntity(category);

    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories(String name, boolean inactivated) {

        List<Category> categories;

        if (StringUtils.hasText(name)) {
            categories = inactivated
                    ? repository.findByNameContainingIgnoreCaseAndActiveFalse(name)
                    : repository.findByNameContainingIgnoreCaseAndActiveTrue(name);
        } else {
            categories = inactivated
                    ? repository.findByActiveFalse()
                    : repository.findByActiveTrue();
        }

        return categories.stream().map(CategoryResponse::fromEntity).toList();

    }

    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryUpdate update) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma categoria cadastrada com esse id: " + id));

        if (update.name() != null && !update.name().isEmpty()) {
            category.setName(update.name());
        }

        if (!category.isActive()) {
            throw new RuntimeException("Ativar essa categoria pra poder atualizar.");
        }

        return CategoryResponse.fromEntity(repository.save(category));

    }

    @Transactional
    public void deleteCategory(UUID id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhuma categoria cadastrada com esse id: " + id));

        category.setActive(false);

    }
}
