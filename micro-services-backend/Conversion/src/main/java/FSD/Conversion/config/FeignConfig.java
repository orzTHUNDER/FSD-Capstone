package FSD.Conversion.config;

import feign.Response;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Configuration
public class FeignConfig {

    @Bean
    public Decoder customDecoder() {
        return (response, type) -> {
            if (response.body() == null) {
                return null;
            }
            String body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            if (type == double.class || type == Double.class) {
                return Double.parseDouble(body);
            }
            throw new IOException("Unsupported response type: " + type);
        };
    }
}