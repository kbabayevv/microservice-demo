package az.company.payments.dao.entity;


import az.company.payments.model.enums.PaymentStatus;
import az.company.payments.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long orderId;

    @Enumerated(STRING)
    private PaymentType paymentType;
    private String referenceNumber;

    @Enumerated(STRING)
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private BigDecimal amount;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
