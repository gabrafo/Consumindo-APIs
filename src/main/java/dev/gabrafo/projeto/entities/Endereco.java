package dev.gabrafo.projeto.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dev.gabrafo.projeto.clients.EnderecoDTO;
import dev.gabrafo.projeto.entities.enums.UF;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;

    @Enumerated(EnumType.STRING)
    private UF uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.cep = enderecoDTO.cep();
        this.logradouro = enderecoDTO.logradouro();
        this.bairro = enderecoDTO.bairro();
        this.localidade = enderecoDTO.localidade();
        this.uf = UF.valueOf(enderecoDTO.uf());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getCep(), endereco.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCep());
    }

    @Override
    public String toString() {
        return "CEP: " + cep + '\n' +
                "Logradouro: '" + logradouro + '\n' +
                "Bairro: '" + bairro + '\n' +
                "Localidade: '" + localidade + '\n' +
                "UF: " + uf;
    }
}