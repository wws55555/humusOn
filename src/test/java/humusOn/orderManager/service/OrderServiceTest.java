package humusOn.orderManager.service;

import humusOn.orderManager.domain.Order;
import humusOn.orderManager.repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderServiceTest {

    private static ClientAndServer mockWebServer;


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("주문을 저장한다.")
    @Test
    void save() {
        //given
        String orderId = "testOrderId";
        String customerName = "testName";
        String date = "testDate";
        String status = "testStatus";
        Order order = Order.builder()
                            .orderId(orderId)
                            .customerName(customerName)
                            .date(date)
                            .status(status)
                            .build();

        //when
        Order savedOrder = orderService.save(order);

        //then
        assertEquals(order, savedOrder);

    }

    @DisplayName("주문ID로 주문을 조회한다.")
    @Test
    void findByOrderId() {
        //given
        String orderId = "testOrderId";
        String customerName = "testName";
        String date = "testDate";
        String status = "testStatus";
        Order order = Order.builder()
                .orderId(orderId)
                .customerName(customerName)
                .date(date)
                .status(status)
                .build();
        orderRepository.save(order);

        //when
        Order foundOrder = orderService.findByOrderId(orderId);

        //then
        assertThat(foundOrder.getOrderId()).isEqualTo(order.getOrderId());
        assertThat(foundOrder.getCustomerName()).isEqualTo(order.getCustomerName());
        assertThat(foundOrder.getDate()).isEqualTo(order.getDate());
        assertThat(foundOrder.getStatus()).isEqualTo(order.getStatus());
    }

    @DisplayName("주문목록을 조회한다..")
    @Test
    void findAll() {
        //given
        String firstOrderId = "testOrderId1";
        String firstCustomerName = "testName1";
        String firstDate = "testDate1";
        String firstStatus = "testStatus1";
        Order firstOrder = Order.builder()
                .orderId(firstOrderId)
                .customerName(firstCustomerName)
                .date(firstDate)
                .status(firstStatus)
                .build();
        orderRepository.save(firstOrder);

        String secondOrderId = "testOrderId2";
        String secondCustomerName = "testName2";
        String secondDate = "testDate2";
        String secondStatus = "testStatus2";
        Order secondOrder = Order.builder()
                .orderId(secondOrderId)
                .customerName(secondCustomerName)
                .date(secondDate)
                .status(secondStatus)
                .build();
        orderRepository.save(secondOrder);

        //when
        List<Order> orders = orderService.findAll();

        //then
        assertThat(orders.size()).isEqualTo(2);
        assertThat(orders.getFirst()).isInstanceOf(Order.class);
        assertThat(orders.getFirst().getOrderId()).isEqualTo(firstOrder.getOrderId());
        assertThat(orders.getLast().getOrderId()).isEqualTo(secondOrder.getOrderId());
    }

}