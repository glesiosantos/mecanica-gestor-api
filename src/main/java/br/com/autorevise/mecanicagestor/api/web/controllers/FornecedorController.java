package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.msoficinas.api.services.FornecedorService;
import br.com.msoficinas.api.web.request.FornecedorFilterRequest;
import br.com.msoficinas.api.web.request.FornecedorRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<?> addFornecedorDoEstabelecimento(@RequestBody @Valid FornecedorRequest request) throws Exception {
        var fornecedor = fornecedorService.salvarFornecedorDoEstabelecimento(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(fornecedor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> carregarFornecedoresDoEstabelecimento(@PathVariable(name = "idEstabelecimento") String idEstabelecimento) {
        var fornecedores = fornecedorService.carregarFornecedoresPeloEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(fornecedores);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarFornecedor(@RequestBody @Valid FornecedorRequest request) throws Exception {
        fornecedorService.editarFornecedor(request);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('PROP')")
    @PostMapping("/remove")
    public ResponseEntity<?> removerFornecedor(@RequestBody @Valid FornecedorFilterRequest request) throws Exception {
        System.out.println("** *** "+request.idEstabelecimento() + "- Fornecedor "+ request.idFornecedor());
        fornecedorService.excluirFornecedorDoEstabelecimento(request.idEstabelecimento(), request.idFornecedor());
        return ResponseEntity.noContent().build();
    }
}
