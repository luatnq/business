package com.example.categoryservice.controller;


import com.example.categoryservice.service.CategoryService;
import com.example.model.category.CategoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDto) {
        log.info("Create category: {}", categoryDto);
        CategoryDTO createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDto) {
        log.info("Update category: {}", categoryDto);
        CategoryDTO updatedCategory = categoryService.editCategory(categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        log.info("Delete category with id = {}", id);
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getCategory(@PathVariable long id) {
        log.info("Search category by id = {}", id);
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAll(){
        log.info("get all categories");
        final List<CategoryDTO> categoryDTOs = categoryService.getAll();
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);

    }
}
