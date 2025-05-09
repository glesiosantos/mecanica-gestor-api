package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.msoficinas.api.services.ServicoEstabelecimentoService;
import br.com.msoficinas.api.web.request.ServicoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/servicos")
public class ServicoEstabelecimentoController {

    @Autowired
    private ServicoEstabelecimentoService servicoService;

    @PostMapping
    public ResponseEntity<?> addServicoEstabelecimento(@RequestBody @Valid ServicoRequest request) throws Exception {
        var servico = servicoService.addServico(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{idEstabelecimento}/{idServico}")
    public ResponseEntity<?> removerServicoEstabelecimento(@PathVariable("idEstabelecimento") String idEstabelecimento, @PathVariable("idServico") String idServico) throws Exception {
        System.out.println();
        servicoService.removerServidoDoEstabelecimento(idEstabelecimento, idServico);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar")
    public ResponseEntity<?> removerServicoEstabelecimento(@RequestBody ServicoRequest request) throws Exception {
        servicoService.atualizarServidoDoEstabelecimento(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> carregarServicoDoEstabelecimento(@PathVariable(value = "idEstabelecimento", required = true) String idEstabelecimento) {
        var servicos = servicoService.carregarServicosDoEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(servicos);
    }
}
