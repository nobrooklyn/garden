package local.garden.rsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.metadata.WellKnownMimeType;
import io.rsocket.transport.netty.client.TcpClientTransport;

@Configuration
public class ClientConfiguration {
    @Bean
    public RSocket rsocket() {
        return RSocketFactory.connect().dataMimeType(MimeTypeUtils.APPLICATION_JSON_VALUE)
                .metadataMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString())
                .frameDecoder(PayloadDecoder.ZERO_COPY).transport(TcpClientTransport.create(7000)).start().block();
    }

    @Bean
    public RSocketRequester rsocketRequester(RSocketStrategies rsocketStrategies) {
        return RSocketRequester.wrap(rsocket(), MimeTypeUtils.APPLICATION_JSON,
                MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_ROUTING.getString()), rsocketStrategies);
    }
}