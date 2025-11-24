// file: graphql-service/src/main/java/com/store/graph/controller/GraphQLProductController.java
package com.store.graph.controller;

import com.store.graph.dto.CreateProductInput;
import com.store.graph.model.ProductEntity;
import com.store.graph.service.ProductService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequiredArgsConstructor
public class GraphQLProductController {

    private final ProductService productService;

    @QueryMapping
    public List<ProductEntity> products() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public ProductEntity product(@Argument UUID id) {
        return productService.getProduct(id);
    }

    @MutationMapping
    @Transactional
    public ProductEntity createProduct(@Argument CreateProductInput input) {
        return productService.createProduct(input.name(), input.price());
    }
}
