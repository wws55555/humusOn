package humusOn.orderManager.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders", indexes = {
        @jakarta.persistence.Index(name = "idx_order_id", columnList = "orderId")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderId;

    private String customerName;

    private String date;

    private String status;

    @Builder
    public Order(
            String orderId,
            String customerName,
            String date,
            String status
    ) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.date = date;
        this.status = status;
    }
}
