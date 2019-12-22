package local.garden.rsocket;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class GreetingRSocketController {
    @MessageMapping("hello")
    public Mono<GreetingData> hello(GreetingRequest request) {
        return Mono.just(new GreetingData("hello " + request.getWord()));
    }

    @MessageExceptionHandler
    public Mono<GreetingData> handleException(Exception e) {
        return Mono.just(GreetingData.fromException(e));
    }
}