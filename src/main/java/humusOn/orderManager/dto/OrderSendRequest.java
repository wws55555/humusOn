package humusOn.orderManager.dto;

import humusOn.orderManager.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderSendRequest {
    private String orderId;
    private String customerName;
    private String date;
    private String status;

    public static OrderSendRequest from(Order order) {
        return new OrderSendRequest(
                order.getOrderId(),
                order.getCustomerName(),
                order.getDate(),
                order.getStatus()
        );
    }
}
