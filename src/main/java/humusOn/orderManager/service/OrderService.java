package humusOn.orderManager.service;

//import humusOn.orderManager.client.OrderSendClient;
import humusOn.orderManager.domain.Order;
import humusOn.orderManager.dto.OrderSendRequest;
import humusOn.orderManager.repository.OrderRepository;
import humusOn.orderManager.support.error.exception.DuplicateOrderException;
import humusOn.orderManager.support.error.exception.NotFoundOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClientService webClientService;

    public Order save(Order order) {
        if (orderRepository.findOrderByOrderId(order.getOrderId()).isPresent()) {
            throw new DuplicateOrderException();
        }
        return orderRepository.save(order);
    }

    public void send(String orderId) {
        Order order = orderRepository.findOrderByOrderId(orderId)
                .orElseThrow(NotFoundOrderException::new);
        webClientService.sendOrder(OrderSendRequest.from(order));
    }

    public Order findByOrderId(String orderId) {
        return orderRepository.findOrderByOrderId(orderId)
                .orElseThrow(NotFoundOrderException::new);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
