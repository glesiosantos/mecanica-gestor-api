package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.enuns.CategoriaProduto;
import br.com.autorevise.mecanicagestor.api.enuns.OrdemStatus;
import br.com.autorevise.mecanicagestor.api.enuns.Perfil;
import br.com.autorevise.mecanicagestor.api.web.response.CategoriaProdutoResponse;
import br.com.autorevise.mecanicagestor.api.web.response.OrdemStatusResponse;
import br.com.autorevise.mecanicagestor.api.web.response.PerfilResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/utils")
public class UtilController {

    @GetMapping("/perfis")
    public ResponseEntity<?> carregarPerfis() {
        List<PerfilResponse> perfis = Arrays.stream(Perfil.values()).map(perfil -> {
            return new PerfilResponse(perfil.name(), perfil.getNome());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/categorias-produtos")
    public ResponseEntity<?> carregarCategoriasProduto() {
        List<CategoriaProdutoResponse> categorias = Arrays.stream(CategoriaProduto.values()).map(c -> new CategoriaProdutoResponse(c.name(), c.getTitulo()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/status/propostas")
    public ResponseEntity<?> carregarStatusProposta() {
        List<OrdemStatusResponse> statusResponses = Arrays.stream(OrdemStatus.values()).map(ordemStatus ->
                new OrdemStatusResponse(ordemStatus.name(), ordemStatus.getDescricao(), ordemStatus.getContexto(), ordemStatus.getProposta().name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(statusResponses);
    }
}
