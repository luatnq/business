package com.example.productservice.service.impl;


import com.example.model.product.ProductDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Caching(
            put = {@CachePut(value = "product", key = "#result.id")},
            evict = {@CacheEvict(value = "listProduct", key = "#productDTO.categoryId")}
    )
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = buildProduct(productDTO);
        log.info("product: {}", product);
        Product createdProduct = productRepository.save(product);
        return ProductMapper.convertToDTO(createdProduct);
    }

    @Override
    @Caching(
            cacheable = {@Cacheable(value = "product", key = "#productDTO.id")},
            put = {@CachePut(value = "product", key = "#productDTO.id")},
            evict = {@CacheEvict(value = "listProduct", key = "#productDTO.categoryId")}
    )
    public ProductDTO editProduct(ProductDTO productDTO) {
        Product product = findById(productDTO.getId());
        product = ProductMapper.convertToEntity(productDTO);
        log.info("product: {}", product);
        Product updatedReview = productRepository.saveAndFlush(product);
        return ProductMapper.convertToDTO(updatedReview);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(value = "product", allEntries = true),
                    @CacheEvict(value = "listProduct", allEntries = true)
            }
    )
    public void deleteById(long id) {
        Product product = findById(id);
        productRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Product findById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
        return product;
    }

    @Override
    public ProductDTO getProductById(long id) {
        Product product = findById(id);
        return ProductMapper.convertToDTO(product);
    }

    @Override
    @Cacheable(value = "listProduct", key = "#categoryId")
    public List<ProductDTO> findProductByCategoryId(long categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        if (products.isEmpty()) throw new ProductNotFoundException();
        return ProductMapper.convertToDTOs(products);
    }

    private Product buildProduct(ProductDTO productDTO) {
        Product product = Product.builder().name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .publisher(productDTO.getPublisher())
                .categoryId(productDTO.getCategoryId())
                .build();
        return product;
    }
}
