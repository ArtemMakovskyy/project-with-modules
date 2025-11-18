package com.store.kafka.repository;

import com.store.kafka.entity.OrderEvent;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEvent, UUID> {
    List<OrderEvent> findByOrderId(String orderId);
}