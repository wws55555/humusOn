package humusOn.orderManager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import humusOn.orderManager.dto.OrderSendRequest;
import humusOn.orderManager.support.error.exception.OrderSendClientException;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class WebClientServiceTest {
    private ClientAndServer mockWebServer;
    private WebClientService webClientService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockWebServer = ClientAndServer.startClientAndServer(1080);
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:1080").build();
        webClientService = new WebClientService(webClient);
    }

    @AfterEach
    void tearDown() {
        mockWebServer.stop();
    }

    @Test
    void sendOrder() throws JsonProcessingException {
        //given
        OrderSendRequest request = new OrderSendRequest("testId", "testName", "testDate", "testStatus");
        String requestStr = objectMapper.writeValueAsString(request);
        mockWebServer
            .when(
                request()
                    .withMethod("POST")
                    .withPath("/send")
                    .withContentType(MediaType.APPLICATION_JSON)
                    .withBody(requestStr)
            )
            .respond(
                response()
                    .withStatusCode(200)
            );
        //when, then
        assertDoesNotThrow(() -> webClientService.sendOrder(request));
    }
}