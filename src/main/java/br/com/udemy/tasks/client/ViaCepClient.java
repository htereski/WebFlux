package br.com.udemy.tasks.client;

import br.com.udemy.tasks.exception.CepNotFoundException;
import br.com.udemy.tasks.model.Address;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ViaCepClient {
    private static final String VIA_CEP_URI = "/{cep}/json";

    private final WebClient client;

    public ViaCepClient(WebClient viaCep) {
        this.client = viaCep;
    }

    public Mono<Address> getAddress(String zipCode) {
        return client
                .get()
                .uri(VIA_CEP_URI, zipCode)
                .retrieve()
                .bodyToMono(Address.class)
                .onErrorResume(error -> Mono.error(CepNotFoundException::new));
    }
}
