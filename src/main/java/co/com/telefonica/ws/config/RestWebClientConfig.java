package co.com.telefonica.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestWebClientConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
