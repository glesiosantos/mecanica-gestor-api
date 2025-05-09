package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.msoficinas.api.services.ClienteService;
import br.com.msoficinas.api.services.EstabelecimentoService;
import br.com.msoficinas.api.web.request.AddVeiculoClienteRequest;
import br.com.msoficinas.api.web.request.ClienteAddRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> addClienteAoEstabelecimento(@RequestBody @Valid ClienteAddRequest request) throws Exception {
        var cliente = clienteService.cadastrarCliente(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/addVeiculo")
    public ResponseEntity<?> addVeiculoAoCliente(@RequestBody AddVeiculoClienteRequest request) throws Exception {
        clienteService.adicionarVeiculoParaCliente(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpfCnpj}")
    public ResponseEntity<?> carregarClientePeloCpfOuCnpj(@PathVariable("cpfCnpj") String cpfCnpj) throws Exception {
        var cliente = clienteService.buscaClienteParaEstabelecimentoPeloCpfOuCnpj(cpfCnpj);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> carregarClientePeloId(@PathVariable("id") String id) throws Exception {
        var cliente = clienteService.buscaClienteParaEstabelecimentoPeloId(id);
        return ResponseEntity.ok(cliente);
    }
}
