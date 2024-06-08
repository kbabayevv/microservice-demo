package az.company.payments.controller;


import az.company.payments.model.request.CreatePaymentRequest;
import az.company.payments.model.response.PaymentResponse;
import az.company.payments.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createPayment(request));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentService.getPaymentById(orderId));
    }
}
