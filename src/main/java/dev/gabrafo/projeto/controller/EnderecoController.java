package dev.gabrafo.projeto.controller;

import dev.gabrafo.projeto.clients.EnderecoDTO;
import dev.gabrafo.projeto.entities.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.gabrafo.projeto.services.EnderecoService;

import java.io.Serial;
import java.io.Serializable;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereço", description = "Endpoints para realizar a consulta de CEP usando a API ViaCep")
public class EnderecoController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private EnderecoService service;

    @Operation(summary = "Busca um endereço",
            description = "Busca um endereço no BD e, caso ele não exista, faz uma requisição à API ViaCep para checar " +
                    "se ele existe ou não. Caso exista, esse endereço é adicionado ao BD para facilitar futuras consultas " +
                    "e também é feito o retorno dos dados ao usuário. É importante destacar que, mesmo sendo um método GET, essa requisição" +
                    "PODE CRIAR NOVOS RECURSOS CASO NECESSÁRIO!",
            tags = {"Endereço"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{cep}", produces = "application/json")
    public ResponseEntity<Endereco> buscarOuCriarEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findEndereco(cep));
    }
}
