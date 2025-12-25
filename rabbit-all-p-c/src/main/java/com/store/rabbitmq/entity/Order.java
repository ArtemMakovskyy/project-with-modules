package com.store.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private Integer id ;

    private String name ;

    private double price ;

    private Integer qty ;
}
