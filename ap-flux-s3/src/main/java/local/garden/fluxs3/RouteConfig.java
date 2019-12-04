package local.garden.fluxs3;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class RouteConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(UploadHandler uploadHandler) {
        return uploadHandler.route();
    }
}