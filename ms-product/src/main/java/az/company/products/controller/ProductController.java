package az.company.products.controller;

import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.request.ReduceQuantityRequest;
import az.company.products.model.response.ProductResponse;
import az.company.products.service.abstraction.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createProduct(@Valid @RequestBody CreateProductRequest request) {
        productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/reduce-quantity")
    @ResponseStatus(NO_CONTENT)
    public void reduceQuantity(@Valid @RequestBody ReduceQuantityRequest reduceQuantityRequest) {
        productService.reduceQuantity(reduceQuantityRequest);
    }
}
