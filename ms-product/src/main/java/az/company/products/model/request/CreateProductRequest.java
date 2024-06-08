package az.company.products.model.request;

import az.company.products.model.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

import static az.company.products.model.constants.ApplicationConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductRequest {
    @NotBlank(message = NAME_IS_REQUIRED)
    private String name;

    @NotBlank(message = DESCRIPTION_IS_REQUIRED)
    private String description;

    @NotNull(message = PRICE_IS_REQUIRED)
    @Positive
    private BigDecimal price;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    @Positive
    private Integer quantity;
}
