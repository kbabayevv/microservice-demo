package az.company.gateway.controller;

import az.company.gateway.model.FallbackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fallback")
public class FallbackController {

    @GetMapping("/productServiceFallBack")
    public FallbackResponse productServiceFallBack() {
        return new FallbackResponse("Product service is down!");
    }

    @GetMapping("/orderServiceFallBack")
    public FallbackResponse orderServiceFallBack() {
        return new FallbackResponse("Order service is down!");
    }

    @GetMapping("/paymentServiceFallBack")
    public FallbackResponse paymentServiceFallBack() {
        return new FallbackResponse("Payment service is down!");
    }
}