package humusOn.orderManager.dto;

import humusOn.orderManager.domain.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영어와 숫자만 입력 가능합니다.")
    private String orderId;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "한글과 영어만 입력 가능합니다.")
    private String customerName;

    @NotBlank
    private String date;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "영어(대소문자)만 입력 가능합니다.")
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
