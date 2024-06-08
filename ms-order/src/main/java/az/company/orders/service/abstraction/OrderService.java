package az.company.orders.service.abstraction;


import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;

public interface OrderService {
    void createOrder(CreateOrderRequest request);
    OrderResponse getOrderById(Long id);
}
