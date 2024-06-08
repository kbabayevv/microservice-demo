package az.company.orders.mapper;



import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.model.client.request.CreatePaymentRequest;
import az.company.orders.model.request.CreateOrderRequest;

import java.math.BigDecimal;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public CreatePaymentRequest buildCreatePaymentRequest(CreateOrderRequest request,
                                                          OrderEntity entity,
                                                          BigDecimal totalAmount) {
        return CreatePaymentRequest.builder()
                .orderId(entity.getId())
                .paymentType(request.getPaymentType())
                .amount(totalAmount)
                .referenceNumber(randomUUID().toString())
                .build();
    }
}
