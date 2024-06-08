package az.company.products.model.request;

import az.company.products.model.constants.ApplicationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static az.company.products.model.constants.ApplicationConstants.PRODUCT_ID_REQUIRED;
import static az.company.products.model.constants.ApplicationConstants.QUANTITY_IS_REQUIRED;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReduceQuantityRequest {
    @NotNull(message = PRODUCT_ID_REQUIRED)
    private Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    private Integer quantity;
}
