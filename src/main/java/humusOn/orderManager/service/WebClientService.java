package humusOn.orderManager.service;

import humusOn.orderManager.domain.Order;
import humusOn.orderManager.dto.OrderSendRequest;
import humusOn.orderManager.support.error.exception.OrderSendClientException;
import humusOn.orderManager.support.error.exception.OrderSendServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebClientService {

    private final WebClient webClient;

    public Mono<Void> sendOrder(OrderSendRequest request) {
        String url = "/send";
        return webClient
                .post()
                .uri(url)
                .bodyValue(request)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new OrderSendClientException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new OrderSendServerException()))
                .bodyToMono(Void.class);
    }
}
