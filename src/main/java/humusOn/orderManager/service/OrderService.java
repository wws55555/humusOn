package humusOn.orderManager.service;

import humusOn.orderManager.domain.Order;
import humusOn.orderManager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void send(Order order) {

    }

    public Order findByOrderId(String orderId) {
        return orderRepository.findOrderByOrderId(orderId)
                .orElseThrow();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
