package az.company.payments.service.concrete;

import az.company.payments.dao.entity.PaymentEntity;
import az.company.payments.dao.repository.PaymentRepository;
import az.company.payments.exception.NotFoundException;
import az.company.payments.model.enums.ErrorMessage;
import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.payments.mapper.PaymentMapper.PAYMENT_MAPPER;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request) {
        PaymentEntity entity = paymentRepository.save(PAYMENT_MAPPER.buildPaymentEntity(request));
        return PAYMENT_MAPPER.buildPaymentResponse(entity);
    }

    @Override
    public PaymentResponse getPaymentById(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .map(PAYMENT_MAPPER::buildPaymentResponse)
                .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.PAYMENT_NOT_FOUND.getMessage(), orderId)));
    }
}
