package local.garden.rsocket;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {
    private final RSocketRequester requester;

    @Autowired
    public GreetingRestController(RSocketRequester requester) {
        this.requester = requester;
    }

    @GetMapping("/hello/{word}")
    public Publisher<GreetingData> current(@PathVariable("word") String word) {
        return requester.route("hello").data(new GreetingRequest(word)).retrieveMono(GreetingData.class);
    }
}