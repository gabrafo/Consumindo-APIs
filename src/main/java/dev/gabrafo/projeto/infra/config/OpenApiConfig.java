package dev.gabrafo.projeto.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Consulta de endereço") // Título da documentação
                        .version("V1") // Versão da documentacao
                        .description("API REST que consome a API ViaCep e utiliza um BD para diminuir o tráfego de requisições") // Descrição da documentação
                )
                .servers(Collections.singletonList(
                        new Server().url("http://localhost:8080").description("Servidor local")
                ));
    }
}
