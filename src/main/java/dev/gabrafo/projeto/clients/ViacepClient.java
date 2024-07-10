package dev.gabrafo.projeto.clients;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep")
@Tag(name = "ViaCep", description = "API para a qual enviamos uma requisição buscando os dados de endereço que iremos" +
        " armazenar em nosso BD para futuras consultas de CEP")
public interface ViacepClient {

    @GetMapping("/{cep}/json")
    EnderecoDTO findEnderecoByCep(@PathVariable String cep);
}