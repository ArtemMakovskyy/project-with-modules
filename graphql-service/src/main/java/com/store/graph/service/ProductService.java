// file: graphql-service/src/main/java/com/store/graph/service/ProductService.java
package com.store.graph.service;

import com.store.graph.model.ProductEntity;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductEntity> getAllProducts();
    ProductEntity getProduct(UUID id);
    ProductEntity createProduct(String name, Double price);
}
