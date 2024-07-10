package dev.gabrafo.projeto.clients;

import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull(message = "CEP não pode ser nulo")
        String cep,

        @NotNull(message = "Logradouro não pode ser nulo")
        String logradouro,

        @NotNull(message = "Bairro não pode ser nulo")
        String bairro,

        @NotNull(message = "Localidade não pode ser nula")
        String localidade,

        @NotNull(message = "UF não pode ser nulo")
        String uf
) {
}
