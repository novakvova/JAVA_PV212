package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.category.CategoryCreateDTO;
import org.example.dto.category.CategoryEditDTO;
import org.example.dto.category.CategoryItemDTO;
import org.example.entites.CategoryEntity;
import org.example.mapper.ICategoryMapper;
import org.example.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private ICategoryRepository categoryRepository;
    private ICategoryMapper categoryMapper;

    public List<CategoryItemDTO> getList() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    public CategoryItemDTO getById(int id) {
        var entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(entity);
    }

    public CategoryEntity create(CategoryCreateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setCreationTime(LocalDateTime.now());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        categoryRepository.save(entity);
        return entity;
    }

//    public CategoryEntity edit(CategoryEditDTO dto) {
//        CategoryEntity entity = categoryRepository.findById(dto.getId()).get();
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setImage(dto.getImage());
//        categoryRepository.save(entity);
//        return entity;
//    }

    public CategoryEntity edit(CategoryEditDTO dto) {
        CategoryEntity entity = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null && !dto.getImage().isBlank()) {
            entity.setImage(dto.getImage());
        }

        return categoryRepository.save(entity);
    }

    public void delete(int id) {
        CategoryEntity entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(entity);
    }
}
