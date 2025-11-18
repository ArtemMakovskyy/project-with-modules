package com.store.kafka.service;

import com.store.kafka.entity.OrderEvent;
import com.store.kafka.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderEvent saveOrder(OrderEvent order) {
        return orderRepository.save(order);
    }

    public List<OrderEvent> getOrdersByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<OrderEvent> getAllOrders() {
        return orderRepository.findAll();
    }
}