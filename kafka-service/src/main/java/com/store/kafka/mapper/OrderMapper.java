package com.store.kafka.mapper;

import com.store.kafka.dto.OrderEventDto;
import com.store.kafka.dto.OrderItemDto;
import com.store.kafka.entity.OrderEvent;
import com.store.kafka.entity.OrderItem;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEventDto toDto(OrderEvent orderEvent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "items", source = "items", qualifiedByName = "mapItemsToEntity")
    OrderEvent toEntity(OrderEventDto orderEventDto);

    OrderItemDto toDto(OrderItem orderItem);

    @Named("mapItemsToEntity")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDto orderItemDto);

    // Если нужен отдельный метод без игнорирования order (редкий случай)
    default OrderItem toEntityWithOrder(OrderItemDto dto, @Context OrderEvent order) {
        OrderItem item = toEntity(dto); // использует метод с @Mapping(ignore)
        item.setOrder(order); // устанавливаем order вручную
        return item;
    }
}
