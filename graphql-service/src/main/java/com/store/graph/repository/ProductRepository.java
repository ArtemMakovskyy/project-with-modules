// graphql-service/src/main/java/com/store/graph/repository/ProductRepository.java
package com.store.graph.repository;

import com.store.graph.model.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
