package br.com.udemy.tasks.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient viaCep(WebClient.Builder buidler,
                            @Value("${via.cep.url}") String url) {
        return getWebClient(buidler, url);
    }

    private WebClient getWebClient(WebClient.Builder builder, String url) {
        return builder
                .uriBuilderFactory(new DefaultUriBuilderFactory(url))
                .build();
    }
}
