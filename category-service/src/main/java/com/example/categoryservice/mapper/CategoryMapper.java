package com.example.categoryservice.mapper;


import com.example.categoryservice.entity.Category;
import com.example.model.category.CategoryDTO;
import com.example.model.review.ReviewDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static CategoryDTO convertToDTO(Category category) {
        final CategoryDTO categoryDto = modelMapper.map(category, CategoryDTO.class);
        return categoryDto;
    }
    public static List<CategoryDTO> convertToDTOs(List<Category> categoryList){
        return categoryList.stream().map(CategoryMapper::convertToDTO).collect(Collectors.toList());
    }
    public static Category convertToEntity(CategoryDTO categoryDto) {
        final Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

}
