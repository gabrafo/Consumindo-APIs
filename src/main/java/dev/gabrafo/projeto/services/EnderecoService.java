package dev.gabrafo.projeto.services;

import dev.gabrafo.projeto.clients.EnderecoDTO;
import dev.gabrafo.projeto.clients.ViacepClient;
import dev.gabrafo.projeto.entities.Endereco;
import dev.gabrafo.projeto.exceptions.InvalidEntryException;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import dev.gabrafo.projeto.exceptions.ClientException;
import dev.gabrafo.projeto.repository.EnderecoRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViacepClient viacepClient;

    @Autowired
    private Validator validator;

    public Endereco findEndereco(String cep) {

        // Tira qualquer caractere que não for um número
        cep = cep.replaceAll("\\D", "");

        // CEP original para checar se já existe no BD
        String cepOriginal = cep.substring(0, 5) + "-" + cep.substring(5);

        if (cep.length()!=8) throw new InvalidEntryException("CEP inválido");

        // Checa se o endereço já está no banco de dados, se sim o retorna
        Optional<Endereco> enderecoJaConsultado = enderecoRepository.findByCep(cepOriginal);
        if(enderecoJaConsultado.isPresent()) return enderecoJaConsultado.get();

        // Tenta fazer a requisição e se ela der certo salva o endereço no banco de dados e retorna os valores cadastrados
        try{
            EnderecoDTO enderecoDTO = viacepClient.findEnderecoByCep(cep);

            // Valida o EnderecoDTO e retorna uma exceção com todos os campos inválidos
            Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                throw new InvalidEntryException("Dados inválidos! " + errorMessage);
            }

            Endereco endereco = new Endereco(enderecoDTO);
            enderecoRepository.save(endereco);
            return endereco;
        } catch (HttpClientErrorException.BadRequest e){
            throw new InvalidEntryException("CEP não encontrado");
        } catch (FeignException.FeignClientException e){
            throw new ClientException("Erro ao consultar o CEP");
        }
    }
}
