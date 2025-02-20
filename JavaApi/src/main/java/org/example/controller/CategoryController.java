package org.example.controller;

import org.example.dto.category.CategoryCreateDTO;
import org.example.dto.category.CategoryEditDTO;
import org.example.dto.category.CategoryItemDTO;
import org.example.entites.CategoryEntity;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryItemDTO> getAllCategories() {
        return categoryService.getList();
    }

    @PostMapping
    public CategoryEntity create(@RequestBody CategoryCreateDTO dto) {
        return categoryService.create(dto);
    }

    @PutMapping("/{id}")
    public CategoryEntity edit(@PathVariable int id, @RequestBody CategoryEditDTO dto) {
        dto.setId(id);
        return categoryService.edit(dto);
    }

    @GetMapping("/{id}")
    public CategoryItemDTO getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
