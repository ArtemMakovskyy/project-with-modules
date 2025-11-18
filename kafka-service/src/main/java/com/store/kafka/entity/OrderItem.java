package com.store.kafka.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @JsonProperty("productId")
        @Column(name = "product_id")
        private String productId;

        @JsonProperty("productName")
        @Column(name = "product_name")
        private String productName;

        @JsonProperty("quantity")
        @Column(name = "quantity")
        private Integer quantity;

        @JsonProperty("price")
        @Column(precision = 19, scale = 2)
        private BigDecimal price;

        @ManyToOne
        @JoinColumn(name = "order_id")
        private OrderEvent order;
}