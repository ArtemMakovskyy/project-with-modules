// file: graphql-service/src/main/java/com/store/graph/service/impl/ProductServiceImpl.java
package com.store.graph.service.impl;

import com.store.graph.model.ProductEntity;
import com.store.graph.repository.ProductRepository;
import com.store.graph.service.ProductService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    @Transactional
    public ProductEntity createProduct(String name, Double price) {
        ProductEntity product = ProductEntity.builder()
                .name(name)
                .price(price)
                .build();
        return productRepository.save(product);
    }
}
