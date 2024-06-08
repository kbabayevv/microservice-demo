package az.company.payments.service.abstraction;


import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(CreatePaymentRequest request);
    PaymentResponse getPaymentById(Long id);
}
