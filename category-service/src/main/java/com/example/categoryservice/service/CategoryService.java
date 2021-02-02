package com.example.categoryservice.service;

import com.example.categoryservice.entity.Category;
import com.example.model.category.CategoryDTO;

import java.util.List;


public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDto);

    CategoryDTO editCategory(CategoryDTO categoryDto);

    void deleteById(long id);

    Category findById(long id);

    CategoryDTO getCategoryById(long id);

    List<CategoryDTO> getAll();
}
