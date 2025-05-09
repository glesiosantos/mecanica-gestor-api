package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> carregarEstabelecimento(@PathVariable("idEstabelecimento") String idEstabelecimento) throws Exception {
        var estabelecimento = estabelecimentoService.carregarDadosDoEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(estabelecimento);
    }

    @GetMapping("/clientes/{idEstabelecimento}")
    public ResponseEntity<?> carregarClientesDoEstabelecimento(@PathVariable(required = true, name = "idEstabelecimento") String idEstabelecimento) {
        System.out.println("idEstabelecimento "+ idEstabelecimento);
        var clientes = estabelecimentoService.carregarClientesDoEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/clientes/{idEstabelecimento}/{idCliente}")
    public ResponseEntity<?> buscarClienteEstabelecimentoPorId(@PathVariable("idEstabelecimento") String idEstabelecimento,
                                                               @PathVariable("idCliente") String idCliente) throws Exception {
        var cliente = estabelecimentoService.carregarClientePeloEstabelecimento(idEstabelecimento, idCliente);
        return ResponseEntity.ok(cliente);
    }
}
