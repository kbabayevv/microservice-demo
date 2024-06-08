package az.company.orders.mapper;


import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.model.client.response.PaymentResponse;
import az.company.orders.model.client.response.ProductResponse;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;

import static az.company.orders.model.enums.OrderStatus.PENDING;
import static java.time.LocalDateTime.*;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity buildOrderEntity(CreateOrderRequest request) {
        return OrderEntity.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status(PENDING)
                .createdAt(now())
                .build();
    }

    public OrderResponse buildOrderResponse(OrderEntity entity, ProductResponse productResponse, PaymentResponse paymentResponse) {
        return OrderResponse.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .amount(entity.getAmount())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .productResponse(productResponse)
                .paymentResponse(paymentResponse)
                .build();
    }

}
