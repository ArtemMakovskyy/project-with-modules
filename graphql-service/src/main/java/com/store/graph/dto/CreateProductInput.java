// file: graphql-service/src/main/java/com/store/graph/dto/CreateProductInput.java
package com.store.graph.dto;

public record CreateProductInput(
        String name,
        Double price
) {
}
