package humusOn.orderManager.dto;

import humusOn.orderManager.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddRequest {
    private String orderId;
    private String customerName;
    private String date;
    private String status;

    public Order toEntity() {
        return Order.builder()
                .orderId(orderId)
                .customerName(customerName)
                .date(date)
                .status(status)
                .build();
    }
}
