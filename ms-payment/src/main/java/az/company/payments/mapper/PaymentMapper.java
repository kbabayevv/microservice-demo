package az.company.payments.mapper;


import az.company.payments.dao.entity.PaymentEntity;
import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;

import static az.company.payments.model.enums.PaymentStatus.SUCCESS;
import static java.time.LocalDateTime.*;

public enum PaymentMapper {
    PAYMENT_MAPPER;

    public PaymentEntity buildPaymentEntity(CreatePaymentRequest request) {
        return PaymentEntity.builder()
                .paymentType(request.getPaymentType())
                .referenceNumber(request.getReferenceNumber())
                .amount(request.getAmount())
                .paymentStatus(SUCCESS)
                .createdAt(now())
                .orderId(request.getOrderId())
                .build();
    }

    public PaymentResponse buildPaymentResponse(PaymentEntity entity) {
        return PaymentResponse.builder()
                .id(entity.getId())
                .paymentType(entity.getPaymentType())
                .paymentStatus(entity.getPaymentStatus())
                .referenceNumber(entity.getReferenceNumber())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
