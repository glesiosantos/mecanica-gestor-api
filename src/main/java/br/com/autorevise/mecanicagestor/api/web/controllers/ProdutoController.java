package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.ProdutoService;
import br.com.autorevise.mecanicagestor.api.web.request.ProdutoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> addProdutoParaEstabelecimento(@RequestBody @Valid ProdutoRequest request) throws Exception {
        var produto = produtoService.addProdutoParaEstabelecimento(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/editar")
    public ResponseEntity<?> editarProdutoParaEstabelecimento(@RequestBody @Valid ProdutoRequest request) throws Exception {
        produtoService.atualizarProdutoByRequest(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> carregarTodosProdutosDoEstabelecimento(@PathVariable("idEstabelecimento") String idEstabelecimento) {
        var produtos = produtoService.carregarProdutosDoEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idProduto}/{idEstabelecimento}")
    public ResponseEntity<?> carregarProdutoPeloId(@PathVariable("idProduto") String idProduto, @PathVariable("idEstabelecimento") String idEstabelecimento) throws Exception {
        var produto = produtoService.carregarProdutoPeloIdProdutoMaisIdEstabelecimento(idProduto, idEstabelecimento);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/codigo/{codigo}/{idEstabelecimento}")
    public ResponseEntity<?> carregarProdutosDoEstabelecimentoPeloCodigo(@PathVariable("codigo") String codigo, @PathVariable("idEstabelecimento") String idEstabelecimento) {
        var produto = produtoService.carregarProdutoPeloSeuCodigo(codigo, idEstabelecimento);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.badRequest().build();
    }
}
