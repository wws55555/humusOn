package humusOn.orderManager.controller;

import humusOn.orderManager.domain.Order;
import humusOn.orderManager.dto.OrderAddRequest;
import humusOn.orderManager.dto.OrderResponse;
import humusOn.orderManager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderAddRequest request) {
        orderService.save(request.toEntity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Void> sendOrder(@PathVariable String orderId) {
        orderService.send(orderId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOrder(@PathVariable String orderId) {
        Order foundOrder = orderService.findByOrderId(orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(OrderResponse.from(foundOrder));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAllOrders() {
        List<OrderResponse> orderResponses = orderService.findAll()
                .stream()
                .map(OrderResponse::from)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderResponses);
    }
}
