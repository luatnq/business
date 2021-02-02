package com.example.productservice.mapper;

import com.example.model.product.ProductDTO;
import com.example.productservice.entity.Product;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProductDTO convertToDTO(Product product) {
        final ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
        return productDto;
    }
    public static List<ProductDTO> convertToDTOs(List<Product> products){
        return products.stream().map(ProductMapper::convertToDTO).collect(Collectors.toList());
    }

    public static Product convertToEntity(ProductDTO productDto) {
        final Product product = modelMapper.map(productDto, Product.class);
        return product;
    }
}
