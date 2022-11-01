package com.luxoft.webflux.rsocket;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.luxoft.webflux.domain.Person;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;
import reactor.netty.tcp.SslProvider;
import reactor.netty.tcp.TcpClient;

import java.io.IOException;
import java.time.Duration;

public class RSocketClient {
    private static Logger log = (Logger) LoggerFactory.getLogger("ROOT");
    static {
        log.setLevel(Level.INFO); // turn off all DEBUG logging
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        RSocketStrategies strategies = RSocketStrategies.builder()
                //.encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
                //.decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
                .encoders(encoders -> encoders.add(new Jackson2JsonEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2JsonDecoder()))
                .build();

        RSocketRequester rsocketRequester = RSocketRequester.builder()
                //.dataMimeType(MediaType.APPLICATION_JSON)
                .rsocketStrategies(strategies)
                .connectTcp("localhost", 7000)
                .block();

        System.out.println("=== Request-Response: Find by id");
        //request response
        rsocketRequester.route("findById")
                .data(1)
                .retrieveMono(Person.class)
                .subscribe(System.out::println);

        Thread.sleep(1000);

        System.out.println("=== Request Stream: retrieve all persons");
        // request stream
        rsocketRequester
                .route("all")
                .retrieveFlux(Person.class)
                .subscribe(System.out::println);

        Thread.sleep(1000);

        System.out.println("=== Channel: get persons by id");
        // channel
        rsocketRequester.route("byId")
                .data(Flux.just(3,2,1))
                .retrieveFlux(Person.class)
                .subscribe(System.out::println);

        Thread.sleep(1000);
    }
}
