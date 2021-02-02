package com.example.productservice.service;

import com.example.model.product.ProductDTO;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO editProduct(ProductDTO productDTO);

    void deleteById(long id);

    Product findById(long id);

    ProductDTO getProductById(long id);

    List<ProductDTO> findProductByCategoryId(long categoryId);

}
