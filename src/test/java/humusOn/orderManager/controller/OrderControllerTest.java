package humusOn.orderManager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import humusOn.orderManager.domain.Order;
import humusOn.orderManager.dto.OrderAddRequest;
import humusOn.orderManager.dto.OrderResponse;
import humusOn.orderManager.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    OrderService orderService = mock();
    OrderController orderController = new OrderController(orderService);

    MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(orderController)
            .build();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("주문을 저장한다.")
    @Test
    void addOrder() throws Exception {
        //given
        String orderId = "testOrderId";
        String customerName = "testCustomerName";
        String date = "testDate";
        String status = "testStatus";
        OrderAddRequest request = new OrderAddRequest(orderId, customerName, date, status);
        Order order = request.toEntity();

        String url = "/orders";

        String requestBody = objectMapper.writeValueAsString(request);

        //when
        when(orderService.save(order)).thenReturn(order);
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());
        verify(orderService).save(any(Order.class));
    }

    @DisplayName("주문을 조회한다.")
    @Test
    void findOrder() throws Exception {
        //given
        String orderId = "testOrderId";
        String customerName = "testCustomerName";
        String date = "testDate";
        String status = "testStatus";
        OrderAddRequest request = new OrderAddRequest(orderId, customerName, date, status);
        Order order = request.toEntity();

        String url = "/orders/{orderId}";

        //when
        when(orderService.findByOrderId(orderId)).thenReturn(order);
        ResultActions result = mockMvc.perform(get(url, orderId)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId))
                .andExpect(jsonPath("$.customerName").value(customerName))
                .andExpect(jsonPath("$.date").value(date))
                .andExpect(jsonPath("$.status").value(status));
    }

    @DisplayName("주문목록을 조회한다.")
    @Test
    void findAllOrders() throws Exception {
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

        List<Order> orderList = new ArrayList<>();
        orderList.add(firstOrder);
        orderList.add(secondOrder);

        String url = "/orders";

        //when
        when(orderService.findAll()).thenReturn(orderList);
        ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(firstOrderId))
                .andExpect(jsonPath("$[0].customerName").value(firstCustomerName))
                .andExpect(jsonPath("$[0].date").value(firstDate))
                .andExpect(jsonPath("$[0].status").value(firstStatus));
    }
}