package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.EstoqueService;
import br.com.msoficinas.api.web.request.EstoqueRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<?> adicionarEstoqueAoProduto(@RequestBody @Valid EstoqueRequest request) throws Exception {
        var estoque = estoqueService.addEstoqueEmProduto(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(estoque.getId()).toUri();
        return ResponseEntity.created(null).build();
    }

    @DeleteMapping("/estoque/{idEstoque}")
    public ResponseEntity<?> removerRegistroDeEstoque(@PathVariable("ieEstoque") String idEstoque) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> carregarRegistroEstoque(String idProduto) {
        var registros = estoqueService.carregarHistoricoEstoqueProduto(idProduto);
        return ResponseEntity.ok(registros);
    }
}
