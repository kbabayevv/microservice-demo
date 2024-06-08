package az.company.payments.model.response;


import az.company.payments.model.enums.PaymentStatus;
import az.company.payments.model.enums.PaymentType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private PaymentType paymentType;
    private String referenceNumber;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
}
