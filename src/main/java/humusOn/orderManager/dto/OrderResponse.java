package humusOn.orderManager.dto;

import humusOn.orderManager.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String customerName;
    private String date;
    private String status;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerName(),
                order.getDate(),
                order.getStatus()
        );
    }
}
