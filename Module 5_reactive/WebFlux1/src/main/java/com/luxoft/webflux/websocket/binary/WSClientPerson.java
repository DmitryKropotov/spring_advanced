package com.luxoft.webflux.websocket.binary;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * We need take in receive() see
 * https://stackoverflow.com/questions/49631757/how-to-ensure-reactive-stream-completes-with-spring-webflux-and-websockets
 * Docs for WebSocketHandler
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-websockethandler
 */
public class WSClientPerson {
    
    public static void main(String[] args) throws InterruptedException {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
            URI.create("ws://localhost:8080/wsbinary"),
            session -> {
                Mono<Void> output = session.send(Flux.just(1,2,3)
                    .delayElements(Duration.ofSeconds(3))
                    .map(Object::toString)
                    .map(session::textMessage)
                    .doOnComplete(()-> System.out.println("output completed"))
                );

                Mono<Void> input = session.receive()
                        .map(WebSocketMessage::getPayload)
                        .map(dataBuffer -> {
                            ByteBuffer buffer = dataBuffer.asByteBuffer();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            return new String(bytes);
                        })
                        .doOnNext(System.out::println)
                        .log()
                        .then();

                return Mono.first(
                        output.then(Mono.delay(Duration.ofSeconds(1))),
                        input)
                    .then();
            })
            .block();

    }
}
