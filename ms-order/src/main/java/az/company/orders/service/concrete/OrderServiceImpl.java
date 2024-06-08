package az.company.orders.service.concrete;

import az.company.orders.client.PaymentClient;
import az.company.orders.client.ProductClient;
import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.dao.repository.OrderRepository;
import az.company.orders.exception.NotFoundException;
import az.company.orders.model.client.request.ReduceQuantityRequest;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;
import az.company.orders.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static az.company.orders.mapper.OrderMapper.ORDER_MAPPER;
import static az.company.orders.mapper.PaymentMapper.PAYMENT_MAPPER;
import static az.company.orders.model.enums.ErrorMessage.ORDER_NOT_FOUND;
import static az.company.orders.model.enums.OrderStatus.APPROVED;
import static az.company.orders.model.enums.OrderStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest request) {
        OrderEntity entity = ORDER_MAPPER.buildOrderEntity(request);
        var productResponse = productClient.getProduct(request.getProductId());

        var totalAmount = productResponse.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
        entity.setAmount(totalAmount);

        var reduceQuantityRequest = new ReduceQuantityRequest(request.getProductId(), request.getQuantity());
        orderRepository.save(entity);
        productClient.reduceQuantity(reduceQuantityRequest);

        try {
            paymentClient.createPayment(
                    PAYMENT_MAPPER.buildCreatePaymentRequest(
                            request,
                            entity,
                            totalAmount)
            );
            entity.setStatus(APPROVED);
        } catch (Exception e) {
            entity.setStatus(REJECTED);
        }
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        var entity = orderRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format(ORDER_NOT_FOUND.getMessage(), id)));

        var productResponse = productClient.getProduct(entity.getProductId());
        var paymentResponse = paymentClient.getPaymentById(id);

        return ORDER_MAPPER.buildOrderResponse(entity, productResponse, paymentResponse);
    }
}
