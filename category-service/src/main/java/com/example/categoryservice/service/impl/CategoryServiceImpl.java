package com.example.categoryservice.service.impl;



import com.example.categoryservice.entity.Category;
import com.example.categoryservice.exception.CategoryNotFoundException;
import com.example.categoryservice.mapper.CategoryMapper;
import com.example.categoryservice.repository.CategoryRepository;
import com.example.categoryservice.service.CategoryService;
import com.example.model.category.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    @Caching(
            evict = {@CacheEvict(value = "category", key = "#result.id")}
    )
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = buildCategory(categoryDto);
        log.info("category: {}", category);
        Category createdCategory = categoryRepository.save(category);
        return CategoryMapper.convertToDTO(createdCategory);
    }

    @Override
    @Transactional
    @Caching(
            evict = {@CacheEvict(value = "category", key = "#result.id")}
    )
    public CategoryDTO editCategory(CategoryDTO categoryDto) {
        Category category = findById(categoryDto.getId());
        category = CategoryMapper.convertToEntity(categoryDto);
        log.info("category: {}", category);
        Category updatedCategory = categoryRepository.saveAndFlush(category);
        return CategoryMapper.convertToDTO(updatedCategory);
    }

    @Override
    @Transactional
    @Caching(
            evict = {@CacheEvict(value = "category", key = "#id")}
    )
    public void deleteById(long id) {
        Category category = findById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "category", key = "#id")
    public Category findById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new CategoryNotFoundException());
        return category;

    }

    @Override
    @Cacheable(value = "category", key = "#id")
    public CategoryDTO getCategoryById(long id) {
        Category category = findById(id);
        return CategoryMapper.convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return CategoryMapper.convertToDTOs(categoryList);
    }

    private Category buildCategory(CategoryDTO categoryDto) {
        Category category = Category.builder().name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .isActive(categoryDto.getIsActive())
                .build();
        return category;
    }
}
