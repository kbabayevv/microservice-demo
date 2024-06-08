package az.company.products.service.concrete;

import az.company.products.dao.entity.ProductEntity;
import az.company.products.dao.repository.ProductRepository;
import az.company.products.exception.InsufficientQuantityException;
import az.company.products.exception.NotFoundException;
import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.request.ReduceQuantityRequest;
import az.company.products.model.response.ProductResponse;
import az.company.products.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static az.company.products.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.company.products.model.enums.ErrorMessage.INSUFFICIENT_QUANTITY;
import static az.company.products.model.enums.ErrorMessage.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductRequest request) {
        productRepository.save(PRODUCT_MAPPER.buildProductEntity(request));
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductEntity productEntity = fetchIfExist(id);
        return PRODUCT_MAPPER.buildProductResponse(productEntity);
    }

    @Override
    public void reduceQuantity(ReduceQuantityRequest reduceQuantityRequest) {
        ProductEntity productEntity = fetchIfExist(reduceQuantityRequest.getProductId());

        if (productEntity.getQuantity() < reduceQuantityRequest.getQuantity()) {
            throw new InsufficientQuantityException(String.format(INSUFFICIENT_QUANTITY.getMessage(), reduceQuantityRequest.getProductId()));
        }

        productEntity.setQuantity(productEntity.getQuantity() - reduceQuantityRequest.getQuantity());
        productRepository.save(productEntity);
    }

    private ProductEntity fetchIfExist(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND.getMessage(), id)));
    }
}
